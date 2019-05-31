package com.pbs.PBshop

import com.pbs.PBshop.Activities.Clientes.Cliente
import org.junit.Test


import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun VerificarExistencia(){
        val list = mutableListOf<Cliente>()
        list.add(
            Cliente(
                4,
                "Yamileth",
                "Rodriguez",
                "6671234567",
                "fakemail4@gmail.com"
            )
        )
        list.add(
            Cliente(
                3,
                "Joaquin",
                "Padilla",
                "6677897548",
                "fakemail3@gmail.com"
            )
        )
        list.add(
            Cliente(
                2,
                "Alejandro",
                "Cardenas",
                "6677774744",
                "fakemail2@gmail.com"
            )
        )
        list.add(Cliente(1, "Felipe", "Mercado", "6677889911", "fakemail@gmail.com"))
        val cliente=
            Cliente(1, "Felipe 1r", "Mercado", "6677889911", "fakemail@gmail.com")
        assertSame(true,existe(list,cliente))
    }

    fun existe(list:MutableList<Cliente>, cliente: Cliente):Boolean
    {
        var existe=false
        for (i in 0 until list.size step 1 ){
            if (list[i].name==cliente.name &&
                list[i].lastName==cliente.lastName &&
                list[i].phone==cliente.phone &&
                list[i].email==cliente.email
            )
                existe=true
        }

        return existe
    }











    @Test
    fun validarNumero(){
        var telefono="6677589955"
        var results=false
        results=checkNu(telefono)
        assertSame(true,results)

    }


    fun checkNu(telefono: String):Boolean{
        var result=false
        if(telefono.length==10)
            result=true


        return result
    }
















    @Test
    fun  validateEmail() {
        var result=false
        val mail="thisdamnFakemailmail.com"
        result=check(mail)
        assert(result)
    }


    fun check(mail:String ):Boolean{
        var nombre=""
        var dominio=""
        var dotcom=""
        var arroba=false
        var punto=false
        var res=false
        for (i in 0 until mail.length step 1) {
            if(mail[i]==('@')&&!arroba ){//solo valida el arroba
                arroba=true
            }

            if(mail[i]!=('@')&&!arroba ){//agrega a nombre
                nombre=nombre.plus(mail[i])
            }

            if(mail[i]==('.')&&!punto){//valida punto
               punto=true
            }else
                if(arroba&&mail[i]!=('.')&&!punto&&mail[i]!=('@')){//agrega caracter a dominio
                    dominio=dominio.plus(mail[i])
                }
            if(arroba&&punto &&mail[i]!='.')//despues del
                dotcom=dotcom.plus(mail[i])
        }
        println(nombre)
        println(dominio)
        println(dotcom)


        if(punto==true&&arroba==true)
            res=true
        return res
    }




}
