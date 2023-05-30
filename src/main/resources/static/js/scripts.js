/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
// Seletor do elemento HTML que exibe a numeração
// Variável para armazenar a quantidade de itens no carrinho
// Variável para armazenar a quantidade de itens no carrinho
let quantidadeItensCarrinho = 0;

// Função para atualizar a quantidade de itens exibida na tela
function atualizarQuantidadeItensCarrinho() {
    const quantidadeItensElement = document.getElementById('quantidade-itens-carrinho');
    quantidadeItensElement.textContent = quantidadeItensCarrinho.toString();
}

// Função para adicionar um item ao carrinho
function adicionarItemAoCarrinho() {
    // Lógica para adicionar o item ao carrinho
    // ...

    // Atualize a quantidade de itens no carrinho
    quantidadeItensCarrinho++;

    // Atualize a quantidade de itens exibida na tela
    atualizarQuantidadeItensCarrinho();
}

// Função para remover um item do carrinho
function removerItemDoCarrinho() {
    // Lógica para remover o item do carrinho
    // ...

    // Verifique se a quantidade de itens é maior que zero antes de decrementar
    if (quantidadeItensCarrinho > 0) {
        quantidadeItensCarrinho--;

        // Atualize a quantidade de itens exibida na tela
        atualizarQuantidadeItensCarrinho();
    }
}
