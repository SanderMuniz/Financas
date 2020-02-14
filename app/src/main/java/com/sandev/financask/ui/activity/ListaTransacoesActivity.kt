package com.sandev.financask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sandev.financask.R
import com.sandev.financask.model.Tipo
import com.sandev.financask.model.Transacao
import com.sandev.financask.ui.ResumoView
import com.sandev.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = configuraTransacoesDeExemplo()

        configuraResumo(transacoes)

        configuraLista(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(view, transacoes)
        resumoView.adicionaReceita()
        resumoView.adicionaDespesa()
        resumoView.adicionaTotal()
    }

    private fun configuraLista(lista: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(lista, this)
    }

    private fun configuraTransacoesDeExemplo(): List<Transacao> {
        val lista = listOf(
            Transacao(
                valor = BigDecimal(20.50),
                tipo = Tipo.DESPESA
            ),
            Transacao(
                valor = BigDecimal(200.50),
                tipo = Tipo.RECEITA,
                categoria = "Almoço de final de semana"
            ),
            Transacao(
                categoria = "Alimentação",
                valor = BigDecimal(20.50),
                tipo = Tipo.DESPESA
            )
        )
        return lista
    }
}