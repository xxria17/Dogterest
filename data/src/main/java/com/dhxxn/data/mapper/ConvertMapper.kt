package com.dhxxn.data.mapper

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

private typealias Mapper<I, O> = (I) -> O
private typealias targetParameterSupplier<O> = () -> O

class ConvertMapper<I: Any, O: Any>(
    private val inType: KClass<I>,
    private val outType: KClass<O>
): Mapper<I, O> {

    private val fieldMappers = mutableMapOf<String, Mapper<Any, Any>>()
    private val targetParameterProviders = mutableMapOf<String, targetParameterSupplier<Any>>()

    private val outConstructor = outType.primaryConstructor!!
    private val inPropertiesByName by lazy { inType.memberProperties.associateBy { it.name }}

    private fun argFor(parameter: KParameter, data: I): Any? {
        val value = inPropertiesByName[parameter.name]?.get(data)
            ?: return targetParameterProviders[parameter.name]?.invoke()

        return fieldMappers[parameter.name]?.invoke(value) ?: value
    }

    override fun invoke(data: I): O = with(outConstructor) {
        callBy(parameters.associateWith { argFor(it, data) })
    }

    companion object {
        inline operator fun <reified I: Any, reified O: Any> invoke() =
            ConvertMapper(I::class, O::class)
    }
}