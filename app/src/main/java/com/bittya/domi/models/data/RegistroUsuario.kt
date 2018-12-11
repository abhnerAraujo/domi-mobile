package com.bittya.domi.models.data

class RegistroUsuario {

    var usuario: Usuario? = null
    var pessoal: Pessoal? = null
    var profissional: Profissional? = null

    class Usuario(var email: String, var senha: String){ var token = ""}
    class Pessoal(var nome: String, var data_nascimento: String, var sexo: String)
    class Profissional(var especialidade: String, var registro: String, var uf: String)
}