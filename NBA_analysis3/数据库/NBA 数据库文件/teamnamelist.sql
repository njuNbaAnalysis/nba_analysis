-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-05-30 06:39:45
-- 服务器版本： 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nba`
--

-- --------------------------------------------------------

--
-- 表的结构 `teamnamelist`
--

CREATE TABLE IF NOT EXISTS `teamnamelist` (
  `teamNameEn` varchar(3) COLLATE utf8_bin NOT NULL,
  `teamNameZh` text COLLATE utf8_bin NOT NULL,
  `conference` varchar(7) COLLATE utf8_bin DEFAULT NULL,
  `division` varchar(9) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `teamnamelist`
--

INSERT INTO `teamnamelist` (`teamNameEn`, `teamNameZh`, `conference`, `division`) VALUES
('ANA', '阿纳海姆搞基', NULL, NULL),
('AND', '安德逊包装工', NULL, NULL),
('ATL', '亚特兰大老鹰', 'eastern', 'southeast'),
('BAL', '巴提摩尔子弹', NULL, NULL),
('BKN', '布鲁克林篮网', 'eastern', 'atlantic'),
('BLB', '巴尔的摩子弹', NULL, NULL),
('BOS', '波士顿凯尔特人', 'eastern', 'atlantic'),
('BUF', '布法罗勇敢者', NULL, NULL),
('CAP', '首都子弹', NULL, NULL),
('CAR', '卡罗来纳美洲狮', NULL, NULL),
('CHA', '夏洛特黄蜂', 'eastern', 'southeast'),
('CHH', '夏洛特黄蜂', NULL, NULL),
('CHI', '芝加哥公牛', 'eastern', 'central'),
('CHP', '芝加哥包装工', NULL, NULL),
('CHS', '芝加哥雄鹿', NULL, NULL),
('CHZ', '芝加哥西风', NULL, NULL),
('CIN', '辛辛纳提皇家', NULL, NULL),
('CLE', '克里夫兰骑士', 'eastern', 'central'),
('CLR', '克里夫兰反叛者', NULL, NULL),
('DAL', '达拉斯小牛', 'western', 'northwest'),
('DEN', '丹佛掘金', 'western', 'southwest'),
('DET', '底特律活塞', 'eastern', 'central'),
('DLC', '达拉斯丛林', NULL, NULL),
('DNA', '丹佛老老掘金', NULL, NULL),
('DNN', '丹佛老掘金', NULL, NULL),
('DNR', '丹佛火箭', NULL, NULL),
('DTF', '底特律猎鹰', NULL, NULL),
('FLO', '佛罗里达人', NULL, NULL),
('FTW', '福特韦恩活塞', NULL, NULL),
('GSW', '金州勇士', 'western', 'pacific'),
('HOU', '休斯顿火箭', 'western', 'northwest'),
('HSM', '休斯顿小牛', NULL, NULL),
('INA', '印第安纳老者', NULL, NULL),
('IND', '印第安纳步行者', 'eastern', 'central'),
('INJ', '印第安纳喷气机', NULL, NULL),
('INO', '印第安纳波利斯', NULL, NULL),
('KCK', '堪萨斯城国王', NULL, NULL),
('KCO', '堪萨奥马哈国王', NULL, NULL),
('KEN', '肯塔基上校', NULL, NULL),
('LAC', '洛杉矶快船', 'western', 'pacific'),
('LAL', '洛杉矶湖人', 'western', 'pacific'),
('LAS', '洛杉矶星光', NULL, NULL),
('MEM', '孟菲斯灰熊', 'western', 'northwest'),
('MIA', '迈阿密热火', 'eastern', 'southeast'),
('MIL', '密尔沃基雄鹿', 'eastern', 'central'),
('MIN', '明尼苏达森林狼', 'western', 'southwest'),
('MLH', '密尔沃基老鹰', NULL, NULL),
('MMF', '迈阿密佛罗里达', NULL, NULL),
('MMP', '孟菲斯职业者', NULL, NULL),
('MMS', '孟菲斯超音速', NULL, NULL),
('MMT', '孟菲斯檀木', NULL, NULL),
('MNL', '明尼阿波利斯湖人', NULL, NULL),
('MNM', '明尼苏达麝香', NULL, NULL),
('MNP', '明尼苏达风笛手', NULL, NULL),
('NJA', '新泽西美洲人', NULL, NULL),
('NJN', '新泽西篮网', NULL, NULL),
('NOB', '新奥尔良海盗', NULL, NULL),
('NOH', '新奥尔良鹈鹕', 'western', 'northwest'),
('NOJ', '新奥尔良爵士', NULL, NULL),
('NYA', '纽约老篮网', NULL, NULL),
('NYK', '纽约尼克斯', 'eastern', 'atlantic'),
('NYN', '纽约篮网', NULL, NULL),
('OAK', '奥克兰橡树', NULL, NULL),
('OKC', '俄克拉荷马雷霆', 'western', 'southwest'),
('ORL', '奥兰多魔术', 'eastern', 'southeast'),
('PHI', '费城76人', 'eastern', 'atlantic'),
('PHO', '菲尼克斯太阳', 'western', 'pacific'),
('PHW', '费城勇士', NULL, NULL),
('PIT', '匹兹堡铁人', NULL, NULL),
('POR', '波特兰开拓者', 'western', 'southwest'),
('PRO', '普罗登斯蒸汽', NULL, NULL),
('PTC', '匹兹堡秃鹰', NULL, NULL),
('PTP', '匹兹堡风笛手', NULL, NULL),
('ROC', '罗切斯特皇家', NULL, NULL),
('SAA', '圣安东尼奥老刺', NULL, NULL),
('SAC', '萨克拉门托国王', 'western', 'pacific'),
('SAS', '圣安东尼奥马刺', 'western', 'northwest'),
('SDA', '圣迭戈征服者', NULL, NULL),
('SDC', '圣地亚哥快船', NULL, NULL),
('SDR', '圣地亚哥火箭', NULL, NULL),
('SDS', '圣地亚哥远航', NULL, NULL),
('SEA', '西雅图超音速', NULL, NULL),
('SFW', '旧金山勇士', NULL, NULL),
('SHE', '西伯根红人', NULL, NULL),
('SSL', '圣路易斯精神', NULL, NULL),
('STB', '圣路易轰炸机', NULL, NULL),
('STL', '圣路易斯老鹰', NULL, NULL),
('SYR', '西拉丘斯国民', NULL, NULL),
('TEX', '德州丛林', NULL, NULL),
('TOR', '多伦多猛龙', 'eastern', 'atlantic'),
('TRH', '多伦多麝香', NULL, NULL),
('TRI', '三城黑鹰', NULL, NULL),
('UTA', '犹他爵士', 'western', 'southwest'),
('UTS', '犹他星光', NULL, NULL),
('VAN', '温哥华灰熊', NULL, NULL),
('VIR', '维吉尼亚大地主', NULL, NULL),
('WAS', '华盛顿奇才', 'eastern', 'southeast'),
('WAT', '滑铁卢老鹰', NULL, NULL),
('WSA', '华盛顿首都', NULL, NULL),
('WSB', '华盛顿子弹', NULL, NULL),
('WSC', '华盛顿老首都', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `teamnamelist`
--
ALTER TABLE `teamnamelist`
  ADD PRIMARY KEY (`teamNameEn`), ADD UNIQUE KEY `teamNameEn` (`teamNameEn`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
