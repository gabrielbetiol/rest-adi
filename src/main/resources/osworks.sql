-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 05-Dez-2020 às 01:43
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `osworks`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `fone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `comentario`
--

CREATE TABLE `comentario` (
  `id` bigint(20) NOT NULL,
  `ordem_servico_id` bigint(20) NOT NULL,
  `descricao` text NOT NULL,
  `data_envio` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ordem_servico`
--

CREATE TABLE `ordem_servico` (
  `id` bigint(20) NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `descricao` text NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `data_abertura` datetime NOT NULL,
  `data_finalizacao` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comentario_ordem_servico` (`ordem_servico_id`);

--
-- Índices para tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ordem_servico_cliente` (`cliente_id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `fk_comentario_ordem_servico` FOREIGN KEY (`ordem_servico_id`) REFERENCES `ordem_servico` (`id`);

--
-- Limitadores para a tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD CONSTRAINT `fk_ordem_servico_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
