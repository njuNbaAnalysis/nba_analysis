-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2015-06-04 14:44:33
-- 服务器版本: 5.6.11
-- PHP 版本: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `nba`
--

-- --------------------------------------------------------

--
-- 表的结构 `teamchineselist`
--

CREATE TABLE IF NOT EXISTS `teamchineselist` (
  `Tid` varchar(10) COLLATE utf8_bin NOT NULL,
  `Cname` varchar(10) COLLATE utf8_bin NOT NULL,
  `Ename` varchar(5) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `teamchineselist`
--

INSERT INTO `teamchineselist` (`Tid`, `Cname`, `Ename`) VALUES
('1610612737', '亚特兰大', 'ATL'),
('1610612738', '波士顿', 'BOS'),
('1610612751', '布鲁克林', 'BKN'),
('1610612766', '夏洛特', 'CHA'),
('1610612741', '芝加哥', 'CHI'),
('1610612739', '克利夫兰', 'CLE'),
('1610612765', '底特律', 'DET'),
('1610612754', '印第安纳', 'IND'),
('1610612748', '迈阿密', 'MIA'),
('1610612749', '密尔沃基', 'MIL'),
('1610612752', '纽约', 'NYK'),
('1610612753', '奥兰多', 'ORL'),
('1610612755', '费城', 'PHI'),
('1610612761', '多伦多', 'TOR'),
('1610612764', '华盛顿', 'WAS'),
('1610612742', '达拉斯', 'DAL'),
('1610612743', '丹佛', 'DEN'),
('1610612744', '金州', 'GSW'),
('1610612745', '休斯顿', 'HOU'),
('1610612746', '洛杉矶', 'LAC'),
('1610612747', '洛杉矶', 'LAL'),
('1610612763', '孟菲斯', 'MEM'),
('1610612750', '明尼苏达', 'MIN'),
('1610612740', '新奥尔良', 'NOP'),
('1610612760', '奥克拉荷马城', 'OKC'),
('1610612756', '菲尼克斯', 'PHX'),
('1610612757', '波特兰', 'POR'),
('1610612758', '萨克拉门托', 'SAC'),
('1610612759', '圣安东尼奥', 'SAS'),
('1610612762', '犹他', 'UTA');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
