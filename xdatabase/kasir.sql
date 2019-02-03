-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Feb 2019 pada 13.12
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
  `hrg_grosir` int(10) NOT NULL,
  `hrg_beli` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `id_kategori`, `id_satuan`, `nm_barang`, `jml_stok`, `hrg_jual`, `hrg_grosir`, `hrg_beli`) VALUES
('089686010015', 'K08', 'S01', 'Indomie Ayam bawang 69gr', 9999, 2500, 0, 2300),
('089686010107', 'K08', 'S01', 'Indomie Kaldu Ayam 75gr', 9999, 2500, 0, 2200),
('089686010947', 'K01', 'S01', 'Indomie Goreng 85gr', 9999, 2500, 0, 2000),
('089686011494', 'K01', 'S01', 'Indomie Soto Padang 75gr', 9999, 2300, 0, 2000),
('089686043297', 'K01', 'S01', 'Indomie Sambal Matah 85gr', 9999, 2500, 0, 2000),
('089686043686', 'K08', 'S01', 'Indomie goreng Iga penyet 80gr', 9999, 2500, 0, 2300),
('089686060003', 'K08', 'S01', 'Pop Mie Ayam bawang 75gr', 9999, 4000, 0, 3500),
('089686060027', 'K08', 'S01', 'Pop Mie Ayam 75gr', 9999, 4000, 0, 3500),
('089686060126', 'K08', 'S01', 'Pop Mie Baso 75gr', 9999, 4000, 0, 3500),
('089686060164', 'K08', 'S01', 'Pop Mie Baso Spesial 75gr', 9999, 4000, 0, 3500),
('089686060744', 'K08', 'S01', 'Pop Mie Goreng Spesial 80gr', 9999, 4000, 0, 3500),
('089686061024', 'K08', 'S01', 'Pop Mie Ayam Bawang 40gr', 9999, 3000, 0, 2500),
('089686061079', 'K08', 'S01', 'Pop Mie Mini Soto 39gr ', 9999, 3000, 0, 2500),
('089686910384', 'K08', 'S01', 'Indomie Soto Spesial 75gr', 9999, 2500, 0, 2200),
('4711036012122', 'K01', 'S01', 'Biskuit delux 300gr', 9999, 6000, 0, 5500),
('4902430710008', 'K04', 'S01', 'Downy banlaw 240ml', 1, 10000, 0, 9500),
('4902430716628', 'K04', 'S01', 'Downy mystique 230ml', 1, 10000, 0, 9500),
('6901668053121', 'K01', 'S01', 'Oreo Vanilla @pcs 68gr', 4, 4500, 0, 3280),
('711844110113', 'K01', 'S02', 'Kecap ABC 135ml', 4, 7000, 0, 6500),
('711844110182', 'K01', 'S01', 'Kecap ABC 65ml', 48, 2000, 0, 1750),
('76164217', 'K06', 'S01', 'Marlboro red 20btng', 9999, 25000, 0, 23375),
('7622210437686', 'K01', 'S01', 'Biskuat cokelat @pcs 10gr', 20, 500, 0, 425),
('7622210580276', 'K01', 'S01', 'Oreo Cokelat @pcs 68gr', 4, 4500, 0, 3280),
('7622210786722', 'K01', 'S01', 'Biskuat original @pcs 60gr', 4, 3000, 0, 2400),
('7622210786739', 'K01', 'S01', 'Biskuat cokelat @pcs 60gr', 4, 3000, 0, 2400),
('7622210828163', 'K01', 'S01', 'Biskuat sandwich cokelat @pcs 118gr', 2, 6500, 0, 5520),
('7622210946553', 'K01', 'S01', 'Biskuat sandwich cokelat @pcs 27gr', 9, 1500, 0, 1230),
('7622300405588', 'K01', 'S01', 'Kejucake @pcs 16gr', 12, 1500, 0, 1290),
('88820309', 'K13', 'S01', 'Cussons baby 150gr', 9999, 7000, 0, 6500),
('8886057883665', 'K09', 'S02', 'Kratingdeng 150ml', 9999, 5000, 0, 4250),
('8888103200013', 'K02', 'S01', 'Cussons baby blue @pcs 75gr', 12, 3500, 0, 2900),
('8888103201010', 'K13', 'S01', 'Cusson baby mild & gentle 75gr', 9999, 4000, 0, 3500),
('8992628010139', 'K10', 'S07', 'Bimoli 5L ', 9999, 67000, 0, 65000),
('8992628020152', 'K10', 'S07', 'Bimoli 2L', 9999, 23000, 0, 21500),
('8992628022156', 'K10', 'S07', 'Bimoli 1L', 9999, 12500, 0, 11000),
('8992696404441', 'K07', 'S05', 'Bear Brand 189ml', 9999, 8500, 0, 8000),
('8992702000063', 'K07', 'S05', 'Susu Indomilk coklat 370 gr', 9999, 9900, 0, 9000),
('8992702000179', 'K07', 'S05', 'Susu Kreamer 370gr', 9999, 8500, 0, 8000),
('8992705012100', 'K01', 'S06', 'Agar-agar satelit', 9999, 2000, 0, 1500),
('8992716108816', 'K01', 'S01', 'Biskuat Original @pcs 140gr', 3, 11000, 0, 9733),
('8992716109189', 'K01', 'S01', 'Biskuat bolu @pcs ', 12, 1500, 0, 1290),
('8992716109462', 'K01', 'S01', 'Biskuat bolu pandan @pcs 16gr', 12, 1500, 0, 1290),
('8992716109561', 'K01', 'S01', 'Biskuat Cokelat @pcs 140gr', 2, 9000, 0, 8162),
('8992727000048', 'K11', 'S01', 'laurier', 9999, 3500, 0, 3000),
('8992745540359', 'K02', 'S01', 'Proclin pemutih 30ml', 100, 500, 0, 420),
('8992747180126', 'K14', 'S02', 'Vixal 500ml', 9999, 12500, 0, 12000),
('8992747180225', 'K14', 'S02', 'Vixal 800ml', 9999, 15000, 0, 14500),
('8992929751090', 'K12', 'S01', 'Kris casual 100ml', 9999, 3200, 0, 3000),
('8992946512285', 'K02', 'S01', 'Shinzui kirei @pcs 85gr', 3, 3500, 0, 3200),
('8992946521133', 'K02', 'S01', 'Active @pcs 80gr', 2, 2000, 0, 1750),
('8992946521416', 'K02', 'S01', 'Shinzui myori @pcs 85gr', 2, 3500, 0, 3200),
('8992946521836', 'K02', 'S01', 'Shinzui kensho @pcs 87gr', 3, 3500, 0, 3200),
('8992946531538', 'K02', 'S01', 'Zen @pcs 105gr', 11, 3000, 0, 2700),
('8992959107539', 'K11', 'S01', 'Softex comfort slim', 9999, 4500, 0, 4000),
('8993007001557', 'K01', 'S06', 'Indomilk putih 37gr', 9999, 1500, 0, 1000),
('8993007001694', 'K01', 'S01', 'Indomilk putih PAK', 9999, 7000, 0, 6500),
('8993007002936', 'K07', 'S05', 'Susu Kreamer 500gr', 9999, 10000, 0, 9000),
('8993189270635', 'K11', 'S01', 'Charm 29cm 8pads', 9999, 8000, 0, 7500),
('8993189320286', 'K11', 'S01', 'Charm Safe Night 35cm 12pads', 9999, 13000, 0, 12500),
('8993335525572', 'K04', 'S01', 'Almeera total 225ml', 3, 5000, 0, 4500),
('8993335525725', 'K02', 'S01', 'Almeera 230ml', 6, 5000, 0, 4500),
('8993335525732', 'K02', 'S01', 'Almeera 105ml', 5, 3000, 0, 2500),
('8993379200886', 'K02', 'S01', 'Harmony orange 70gr', 41, 1900, 0, 1500),
('8993379500313', 'K02', 'S01', 'Champion red @pcs ', 9, 1500, 0, 1250),
('8993379500320', 'K02', 'S01', 'Champion blue @pcs 60gr', 7, 1500, 0, 1250),
('8993417112270', 'K12', 'S02', 'Cologne Special Day 100ml', 9999, 12500, 0, 12000),
('8993417212116', 'K12', 'S02', 'Cologne Monday 50ml', 9999, 7500, 0, 7000),
('8993417212123', 'K12', 'S02', 'Cologne Gel tuesday 50ml', 9999, 7500, 0, 7000),
('8993417212147', 'K12', 'S02', 'Cologne thursday 50ml', 9999, 7500, 0, 7000),
('8993417212161', 'K12', 'S02', 'Colegne Saturday 50ml', 9999, 7500, 0, 7000),
('8993417212178', 'K12', 'S02', 'Cologne Sunday gel 50ml', 9999, 7500, 0, 7000),
('8993417212215', 'K12', 'S02', 'Cologne monday 100ml', 9999, 13000, 0, 12500),
('8993417212253', 'K12', 'S02', 'Cologne Gel friday 100ml', 9999, 12500, 0, 12000),
('8993417212260', 'K12', 'S01', 'Cologne Gel saturday 100ml', 9999, 12500, 0, 12000),
('8993560024635', 'K02', 'S01', 'Dettol active 65g', 8, 3400, 0, 3200),
('8993560024642', 'K02', 'S01', 'Dettol active 105gr', 6, 5300, 0, 5000),
('8993560024987', 'K02', 'S01', 'Dettol original 105gr', 6, 5300, 0, 5000),
('8993560025007', 'K02', 'S01', 'Dettol cool @pcs 105gr', 6, 5300, 0, 5000),
('8993560025014', 'K02', 'S01', 'Dettol cool 65gr', 6, 3400, 0, 3200),
('8993560025038', 'K02', 'S01', 'dettol fresh 65gr', 5, 3400, 0, 3200),
('8993560025069', 'K02', 'S01', 'Dettol sensitive 105gr', 6, 5300, 0, 5000),
('8993560025083', 'K02', 'S01', 'Dettol skincare', 6, 5300, 0, 5000),
('8993560025090', 'K02', 'S01', 'Dettol skincare 65gr', 5, 3400, 0, 3200),
('8993560025397', 'K02', 'S01', 'Dettol deep cleanse 105 gr', 5, 5300, 0, 5000),
('8993560025410', 'K02', 'S01', 'Dettol deep cleanse 65gr', 8, 3400, 0, 3200),
('8993560033293', 'K04', 'S01', 'Vanish cair 150ml', 12, 7500, 0, 7000),
('8993560033477', 'K04', 'S01', 'Vanish cair 60ml', 22, 3000, 0, 2500),
('8995205301231', 'K12', 'S01', 'Cottonbuds', 9999, 2500, 0, 2000),
('8996168442184', 'K01', 'S02', 'Kecap Sate 275ml', 1, 12500, 0, 11500),
('8996168442191', 'K01', 'S01', 'Kecap sate @pcs 525ml', 7, 16500, 0, 15000),
('8997009510017', 'K01', 'S02', 'C 1000 Lemon 140ml', 9999, 5500, 0, 5000),
('8997015660140', 'K10', 'S01', 'Minya Goreng LIZA 450ml', 9999, 5500, 0, 5000),
('8998103000534', 'K02', 'S01', 'Cusson baby red @pcs 75gr', 10, 3500, 0, 2900),
('8998103000565', 'K13', 'S01', 'Cusson Soft & Smooth', 9999, 4000, 0, 3500),
('8998103011806', 'K13', 'S01', 'Cusson Baby Fresh & Nourish 75gr', 9999, 4000, 0, 3500),
('89981108', 'K06', 'S01', 'Lucky Strike 20btng', 9999, 20000, 0, 19000),
('8998127514123', 'K06', 'S01', 'Dunhill 20btng', 9999, 21500, 0, 20000),
('8998389112433', 'K01', 'S01', 'Kokola Strawberry 200gr', 10, 5000, 0, 4500),
('8998866181068', 'K03', 'S01', 'Ciptadent fresh mint 75gr', 17, 3500, 0, 2800),
('8998866600569', 'K04', 'S01', 'Soklin apel 400ml', 1, 5500, 0, 5000),
('8998866608039', 'K01', 'S01', 'Kecap Sedap 70ml', 32, 2000, 0, 1750),
('8998866608084', 'K01', 'S01', 'Kecap Sedap 600ml', 8, 18500, 0, 17500),
('8998866608602', 'K02', 'S01', 'Giv white 76gr', 36, 1660, 0, 1500),
('8998866608824', 'K01', 'S02', 'Kecap Sedap 720ml', 5, 24000, 0, 23000),
('8998866803731', 'K04', 'S01', 'Super sol 60ml', 16, 1000, 0, 750),
('8998866809245', 'K04', 'S01', 'Soklin lantai 80ml', 28, 1000, 0, 750),
('8998989110129', 'K06', 'S01', 'Surya 12btng', 9999, 16000, 0, 15800),
('8998989300391', 'K06', 'S01', 'Surya Pro fessional', 9999, 22000, 0, 21600),
('8999908010605', 'K12', 'S01', 'Marina bright fresh 100ml', 9999, 5000, 0, 4500),
('8999908034205', 'K09', 'S02', 'Hemaviton 150ml', 9999, 4500, 0, 4000),
('8999908045607', 'K12', 'S01', 'Marina nutri fresh 100ml', 9999, 4500, 0, 4000),
('8999999008505', 'K03', 'S01', 'Pepsodent sensitive expert', 3, 22000, 0, 21000),
('8999999012625', 'K01', 'S01', 'Kecap bango @pcs 220ml', 5, 10500, 0, 9500),
('8999999031107', 'K02', 'S01', 'Lux lily fresh 80gr', 1, 3500, 0, 3000),
('8999999036348', 'K02', 'S02', 'Sunlight jeruk nipis 400ml', 2, 12500, 0, 12000),
('8999999036607', 'K02', 'S01', 'Lux soft rose 80gr', 6, 3500, 0, 3000),
('8999999036638', 'K02', 'S01', 'Lux velvet jasmine 80gr', 4, 3000, 0, 2500),
('8999999036676', 'K02', 'S01', 'Lux aqua delight 80gr', 6, 3500, 0, 3000),
('8999999036690', 'K02', 'S01', 'Lux magical spell 80gr', 26, 3500, 0, 3000),
('8999999049034', 'K02', 'S01', 'Sunlight jeruk nipis 50ml', 5, 1000, 0, 750),
('8999999050009', 'K02', 'S01', 'Sunlight jeruk nipis 105ml', 42, 2000, 0, 1500),
('8999999059309', 'K02', 'S01', 'Lifebuoy red @pcs 80gr', 18, 3000, 0, 2750),
('8999999059316', 'K02', 'S01', 'Lifebuoy mild care 80gr', 23, 3000, 0, 2500),
('8999999059323', 'K02', 'S01', 'Lifebuoy lemont fresh 80gr', 4, 3000, 0, 2500),
('8999999059330', 'K02', 'S01', 'Lfiebuoy nature pure 80gr', 3, 3000, 0, 2500),
('8999999059781', 'K02', 'S01', 'Sunlight jeruk nipis 220ml', 14, 5000, 0, 4500),
('8999999100506', 'K01', 'S01', 'Kecap bango @pcs 575ml', 8, 22500, 0, 21000),
('8999999390198', 'K02', 'S01', 'Sunlight jeruk nipis 900ml', 3, 15000, 0, 14000),
('8999999400903', 'K04', 'S01', 'Molto pewangi 450ml', 2, 6000, 0, 5500),
('8999999400958', 'K04', 'S01', 'Molto pewangi blue 900ml', 3, 10500, 0, 10000),
('8999999401023', 'K04', 'S01', 'Molto pengawi red 900ml', 1, 10500, 0, 10000),
('8999999500399', 'K02', 'S01', 'Lux camellia white 80gr', 3, 3000, 0, 2500),
('8999999500672', 'K14', 'S02', 'Vixal 200ml', 9999, 5000, 0, 4500),
('8999999514006', 'K01', 'S01', 'Kecap bango @pcs 60ml', 2, 3000, 0, 2000),
('8999999521004', 'K04', 'S01', 'Super pell aple 111ml', 2, 2000, 0, 1500),
('8999999521011', 'K04', 'S01', 'Super pell lemon 111ml', 31, 2000, 0, 1500),
('8999999524722', 'K02', 'S01', 'Sunlight jeruk nipis 455ml', 2, 10000, 0, 9000),
('8999999528850', 'K12', 'S01', 'Citra Natural Glowing 60ml', 9999, 6000, 0, 5500),
('8999999533717', 'K02', 'S01', 'Citra hijau @pcs 70gr', 8, 2250, 0, 1900),
('8999999533724', 'K02', 'S01', 'Citra Pearly White 70gr', 22, 2250, 0, 1900),
('8999999533731', 'K02', 'S01', 'Citra bengkoang @pcs 70gr', 48, 2250, 0, 1900),
('8999999534097', 'K03', 'S01', 'Pepsodent cool mint 75gr', 5, 3500, 0, 3000),
('8999999706081', 'K03', 'S01', 'Pepsodent 75gr', 8, 4000, 0, 3750),
('8999999706111', 'K03', 'S01', 'Pepsodent 25gr', 26, 2000, 0, 1800),
('8999999706173', 'K03', 'S01', 'Pepsoden 120gr', 6, 6500, 0, 6000),
('8999999706180', 'K03', 'S01', 'Pepsodent 190gr', 7, 10500, 0, 9750),
('8999999707859', 'K03', 'S01', 'Close up menthol fresh 160gr', 2, 13500, 0, 13000),
('8999999710866', 'K03', 'S01', 'Pepsodent action 123 herbal 190gr ', 1, 16000, 0, 15000),
('8999999710880', 'K03', 'S01', 'Pepsoden action 123 herbal 75gr', 7, 6500, 0, 5500);

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

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail_transaksi`, `id_transaksi`, `id_barang`, `qty`, `total_hrg`) VALUES
(1, 'T000000001', '8992705012100', 10, 30000),
(2, 'T000000001', '8992696404441', 20, 200000),
(3, 'T000000002', '8992705012100', 5, 10000),
(4, 'T000000002', '8992696404441', 5, 42500),
(5, 'T000000003', '8992705012100', 5, 10000),
(6, 'T000000003', '8992696404441', 5, 42500);

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
('D001', 'test', 'test', 'test'),
('D002', 'testtest', 'test2', 'test2');

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
('K01', 'pangan'),
('K02', 'sabun'),
('K03', 'odol'),
('K04', 'pewangi'),
('K05', 'semir'),
('K06', 'Rokok'),
('K07', 'susu'),
('K08', 'mie'),
('K09', 'minuman'),
('K10', 'minyak'),
('K11', 'Pembalut'),
('K12', 'kosmetik'),
('K13', 'Bedak'),
('K14', 'pembersih');

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
('S02', 'botol'),
('S03', 'gr'),
('S04', 'kg'),
('S05', 'kaleng'),
('S06', 'sachet'),
('S07', 'liter');

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
('T000000001', 'U01', 230000, 230000, 0, '2019-02-01'),
('T000000002', 'U01', 52500, 60000, 7500, '2019-02-01'),
('T000000003', 'U01', 52500, 55000, 2500, '2019-02-01');

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
('U02', 'haha', 'haha', 'jaja', 'Kasir'),
('U03', 's', 's', 's', 'Gudang'),
('U04', 'kasir', 'kasir', 'kasir', 'Kasir'),
('U05', 'gudang', 'gudang', 'gudang', 'Gudang');

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
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

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
  MODIFY `id_detail_pemasokan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
