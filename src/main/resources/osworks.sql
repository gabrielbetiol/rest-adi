-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 27/01/2021 às 16:12
-- Versão do servidor: 10.4.13-MariaDB
-- Versão do PHP: 7.4.8

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
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `fone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `email`, `senha`, `fone`) VALUES
(2, 'joao updated', 'joao_up@email.com', '1234567', '12345678'),
(3, 'joao', 'joao@email.com', '1234567', '12345678'),
(4, 'Adriane', 'adriane@email', '12345678', '12345678'),
(5, 'jose', 'jose@email.com', '12345678', '123456'),
(6, 'pedro', 'pedro@email.com', '12345678', '12345678'),
(7, 'josep', 'josep@email.com', '1234567', '123456');

-- --------------------------------------------------------

--
-- Estrutura para tabela `comentario`
--

CREATE TABLE `comentario` (
  `id` bigint(20) NOT NULL,
  `ordem_servico_id` bigint(20) NOT NULL,
  `descricao` text NOT NULL,
  `data_envio` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `comentario`
--

INSERT INTO `comentario` (`id`, `ordem_servico_id`, `descricao`, `data_envio`) VALUES
(1, 1, 'Essa bagaça precisa ser consertada antes do natal.', '2020-12-23 17:43:38');

-- --------------------------------------------------------

--
-- Estrutura para tabela `ordem_servico`
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
-- Despejando dados para a tabela `ordem_servico`
--

INSERT INTO `ordem_servico` (`id`, `cliente_id`, `descricao`, `preco`, `status`, `data_abertura`, `data_finalizacao`) VALUES
(1, 2, 'Instalar 2 cameras', '150.00', 'FINALIZADA', '2020-12-18 00:49:12', NULL),
(2, 4, 'Reparo de placa mae', '150.00', 'ABERTA', '2020-12-20 02:55:11', NULL),
(3, 3, 'Troca de interruptor', '50.20', 'CANCELADA', '2020-12-18 00:49:12', NULL),
(4, 2, 'Limpeza do CPU', '137.82', 'FINALIZADA', '2020-12-20 02:55:11', '2020-12-25 14:47:00'),
(5, 4, 'Instalar Tvs ', '35.68', 'ABERTA', '2020-12-24 17:40:52', NULL);

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comentario_ordem_servico` (`ordem_servico_id`);

--
-- Índices de tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ordem_servico_cliente` (`cliente_id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `fk_comentario_ordem_servico` FOREIGN KEY (`ordem_servico_id`) REFERENCES `ordem_servico` (`id`);

--
-- Restrições para tabelas `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD CONSTRAINT `fk_ordem_servico_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
