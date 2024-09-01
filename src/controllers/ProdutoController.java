package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {

	private Scanner scanner = new Scanner(System.in);

	public void cadastrarProduto() {

		try {
			System.out.println("\nCADASTRO DE PRODUTOS:\n");

			System.out.print("NOME DO PRODUTOS...:");
			var nome = scanner.nextLine();

			System.out.print("PREÇO...............:");
			var preco = Double.parseDouble(scanner.nextLine());

			System.out.print("QUANTIDADE...........:");
			var quantidade = Integer.parseInt(scanner.nextLine());

			var produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);

			var produtoRepository = new ProdutoRepository();

			produtoRepository.inserir(produto);

		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar o produto!");
			System.out.println(e.getMessage());
		}
	}

	public void atualizarProduto() {

		try {
			System.out.println("\nATUALIZAÇÃO DE PRODUTOS: \n");

			System.out.print("INFORME O ID DO PRODUTO.: ");
			var id = UUID.fromString(scanner.nextLine());

			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);

			if (produto != null) {

				System.out.println("\nDADOS DE PRODUTOS:");
				System.out.println("ID........:" + produto.getId());
				System.out.println("NOME......:" + produto.getNome());
				System.out.println("PREÇO.....:" + produto.getPreco());
				System.out.println("QUANTIDADE:" + produto.getQuantidade());
				System.out.println("");

				System.out.print("ALTERE O NOME........:");
				produto.setNome(scanner.nextLine());

				System.out.print("ALTERE O PREÇO........:");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));

				System.out.print("ALTERE O QUANTIDADE..:");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));

				produtoRepository.atualizar(produto);

			} else {
				System.out.println("\nProduto não encontrado.Verifique o ID informado.");
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar produto");
			System.out.println(e.getMessage());
		}

	}

	public void excluirProduto() {

		try {
			System.out.println("\nEXCLUSÃO DE PRODUTOS: \n");

			System.out.print("INFORME O ID DO PRODUTO.: ");
			var id = UUID.fromString(scanner.next());

			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);

			if (produto != null) {

				System.out.println("\nDADOS DE PRODUTOS:");
				System.out.println("ID........: " + produto.getId());
				System.out.println("NOME......: " + produto.getNome());
				System.out.println("PREÇO.....: " + produto.getPreco());
				System.out.println("QUANTIDADE: " + produto.getQuantidade());
				System.out.println("");

				produtoRepository.excluir(produto.getId());

			} else {
				System.out.println("\nProduto não encontrado.Verifique o ID informado.");
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar produto");
			System.out.println(e.getMessage());
		}

	}

	public void consultarProdutos() {
		try {

			System.out.println("\nCONSULTA DE PRODUTOS: \n");
			
			var produtoRepository = new ProdutoRepository();
			var lista = produtoRepository.consultar();
			
			for(Produto produto : lista) {
				
				System.out.println("ID........: " + produto.getId());
				System.out.println("NOME......: " + produto.getNome());
				System.out.println("PREÇO.....: " + produto.getPreco());
				System.out.println("QUANTIDADE: " + produto.getQuantidade());
				System.out.println("");
				
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao consultar produto.");
			System.out.println(e.getMessage());
		}
	}
}
