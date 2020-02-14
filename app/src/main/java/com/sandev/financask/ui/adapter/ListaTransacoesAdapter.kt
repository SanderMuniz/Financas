package com.sandev.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.sandev.financask.R
import com.sandev.financask.extension.formataParaBrasileiro
import com.sandev.financask.extension.limitaEmAte
import com.sandev.financask.model.Tipo
import com.sandev.financask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>,
    private val context: Context
) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val viewCriada =
            LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        adicionaValor(viewCriada, transacao)
        adicionaIcone(transacao, viewCriada)
        adicionaData(viewCriada, transacao)
        adicionaCategoria(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaCategoria(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaData(
        viewCriada: View,
        transacao: Transacao
    ) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaIcone(
        transacao: Transacao,
        viewCriada: View
    ) {

        val icone = iconePorTipo(transacao.tipo)

        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePorTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa

    }

    private fun adicionaValor(
        viewCriada: View,
        transacao: Transacao
    ) {
        val cor: Int = corPorTipo(transacao.tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPorTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)

    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }


}