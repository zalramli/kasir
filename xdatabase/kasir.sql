-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Jan 2019 pada 06.51
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(30) NOT NULL,
  `id_kategori` char(3) NOT NULL,
  `id_satuan` char(3) NOT NULL,
  `nm_barang` varchar(50) NOT NULL,
  `jml_stok` smallint(5) NOT NULL,
  `hrg_jual` int(10) NOT NULL,
  `hrg_beli` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `id_kategori`, `id_satuan`, `nm_barang`, `jml_stok`, `hrg_jual`, `hrg_beli`) VALUES
('8992222091718', 'k02', 'S02', 'parfum', 18, 15000, 12000),
('8999999036348', 'K03', 'S01', 'sinlight', 100, 17000, 15000),
('8999999053291', 'k01', 'S01', 'lifeboy ', 30, 3000, 2500);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pemasokan`
--

CREATE TABLE `detail_pemasokan` (
  `id_detail_pemasokan` int(11) NOT NULL,
  `id_pemasokan` char(8) NOT NULL,
  `id_barang` varchar(30) NOT NULL,
  `qty` smallint(5) NOT NULL,
  `total_hrg` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_pemasokan`
--

INSERT INTO `detail_pemasokan` (`id_detail_pemasokan`, `id_pemasokan`, `id_barang`, `qty`, `total_hrg`) VALUES
(1, 'P0000001', '8992222091718', 1, 12000),
(2, 'P0000002', '8999999036348', 1, 15000),
(3, 'P0000003', '8992222091718', 1, 12000),
(4, 'P0000004', '8999999036348', 1, 15000),
(5, 'P0000005', '8999999036348', 12, 180000),
(6, 'P0000006', '8999999036348', 14, 210000),
(7, 'P0000007', '8999999036348', 20, 300000),
(8, 'P0000008', '8999999036348', 4, 60000),
(9, 'P0000009', '8999999036348', 4, 60000),
(10, 'P0000010', '8999999036348', 90, 1350000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail_transaksi` int(11) NOT NULL,
  `id_transaksi` char(10) NOT NULL,
  `id_barang` varchar(30) NOT NULL,
  `qty` smallint(5) NOT NULL,
  `total_hrg` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `distributor`
--

CREATE TABLE `distributor` (
  `id_distributor` char(4) NOT NULL,
  `nm_distributor` varchar(50) NOT NULL,
  `no_hp` char(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `distributor`
--

INSERT INTO `distributor` (`id_distributor`, `nm_distributor`, `no_hp`, `alamat`) VALUES
('D001', 'hahaha', '08123123', 'aseda'),
('D002', 'hehehe', '87', 'qwewqe'),
('D003', 'ghj', 'hgj', 'ghj');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` char(3) NOT NULL,
  `nm_kategori` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nm_kategori`) VALUES
('k01', 'sandang'),
('k02', 'pangan'),
('K03', 'papan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemasokan`
--

CREATE TABLE `pemasokan` (
  `id_pemasokan` char(8) NOT NULL,
  `id_distributor` char(4) NOT NULL,
  `id_user` char(3) NOT NULL,
  `total_pasok` int(10) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL,
  `tgl_pemasokan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pemasokan`
--

INSERT INTO `pemasokan` (`id_pemasokan`, `id_distributor`, `id_user`, `total_pasok`, `bayar`, `kembalian`, `tgl_pemasokan`) VALUES
('P0000001', 'null', 'U01', 12000, 100000, 88000, '2019-01-26'),
('P0000002', 'D001', 'U01', 15000, 100000, 85000, '2019-01-26'),
('P0000003', 'D002', 'U01', 12000, 100000, 88000, '2019-01-26'),
('P0000004', 'D002', 'U01', 15000, 120000, 105000, '2019-01-26'),
('P0000005', 'D001', 'U01', 180000, 200000, 20000, '2019-01-26'),
('P0000006', 'D002', 'U01', 210000, 1000000, 790000, '2019-01-26'),
('P0000007', 'D002', 'U01', 300000, 300000, 0, '2019-01-26'),
('P0000008', 'D002', 'U01', 60000, 111111, 51111, '2019-01-26'),
('P0000009', 'D002', 'U01', 60000, 70000, 10000, '2019-01-26'),
('P0000010', 'D002', 'U01', 1350000, 10000000, 8650000, '2019-01-26');

-- --------------------------------------------------------

--
-- Struktur dari tabel `satuan`
--

CREATE TABLE `satuan` (
  `id_satuan` char(3) NOT NULL,
  `nm_satuan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `satuan`
--

INSERT INTO `satuan` (`id_satuan`, `nm_satuan`) VALUES
('S01', 'pcs'),
('S02', 'dos'),
('S03', 'kg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(10) NOT NULL,
  `id_user` char(3) NOT NULL,
  `total_hrg` int(10) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `total_hrg`, `bayar`, `kembalian`, `tgl_transaksi`) VALUES
('T000000001', 'U01', 24000, 30000, 6000, '2019-01-22'),
('T000000002', 'U01', 60000, 100000, 40000, '2019-01-23'),
('T000000003', 'U01', 62500, 80000, 17500, '2019-01-23'),
('T000000004', 'U01', 105000, 200000, 95000, '2019-01-23'),
('T000000005', 'U01', 105000, 120000, 15000, '2019-01-23'),
('T000000006', 'U01', 106000, 120000, 14000, '2019-01-23');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` char(3) NOT NULL,
  `nm_user` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `akses` enum('Admin','Kasir','Gudang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `nm_user`, `username`, `password`, `akses`) VALUES
('U01', 'kika', 'kika', 'kika', 'Admin'),
('U02', 'haha', 'haha', 'haha', 'Kasir'),
('U03', 's', 's', 's', 'Gudang');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `detail_pemasokan`
--
ALTER TABLE `detail_pemasokan`
  ADD PRIMARY KEY (`id_detail_pemasokan`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail_transaksi`);

--
-- Indeks untuk tabel `distributor`
--
ALTER TABLE `distributor`
  ADD PRIMARY KEY (`id_distributor`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `pemasokan`
--
ALTER TABLE `pemasokan`
  ADD PRIMARY KEY (`id_pemasokan`);

--
-- Indeks untuk tabel `satuan`
--
ALTER TABLE `satuan`
  ADD PRIMARY KEY (`id_satuan`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_pemasokan`
--
ALTER TABLE `detail_pemasokan`
  MODIFY `id_detail_pemasokan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail_transaksi` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
