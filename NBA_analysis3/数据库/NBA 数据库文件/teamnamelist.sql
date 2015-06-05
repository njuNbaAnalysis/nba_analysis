-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-06-05 18:36:29
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
  `division` varchar(9) COLLATE utf8_bin DEFAULT NULL,
  `teamCode` text COLLATE utf8_bin,
  `teamNameZhAbbr` text COLLATE utf8_bin,
  `homecourt` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `teamnamelist`
--

INSERT INTO `teamnamelist` (`teamNameEn`, `teamNameZh`, `conference`, `division`, `teamCode`, `teamNameZhAbbr`, `homecourt`) VALUES
('ANA', '阿纳海姆搞基', NULL, NULL, NULL, NULL, NULL),
('AND', '安德逊包装工', NULL, NULL, NULL, NULL, NULL),
('ATL', '亚特兰大老鹰', 'eastern', 'southeast', 'hawks', '老鹰', 'Philips Arena'),
('BAL', '巴提摩尔子弹', NULL, NULL, NULL, NULL, NULL),
('BKN', '布鲁克林篮网', 'eastern', 'atlantic', 'nets', '篮网', 'Barclays Center'),
('BLB', '巴尔的摩子弹', NULL, NULL, NULL, NULL, NULL),
('BOS', '波士顿凯尔特人', 'eastern', 'atlantic', 'celtics', '凯尔特', 'TD Garden'),
('BUF', '布法罗勇敢者', NULL, NULL, NULL, NULL, NULL),
('CAP', '首都子弹', NULL, NULL, NULL, NULL, NULL),
('CAR', '卡罗来纳美洲狮', NULL, NULL, NULL, NULL, NULL),
('CHA', '夏洛特黄蜂', 'eastern', 'southeast', 'hornets', '黄蜂', 'Time Warner Cable Arena'),
('CHH', '夏洛特黄蜂', NULL, NULL, NULL, '黄蜂', NULL),
('CHI', '芝加哥公牛', 'eastern', 'central', 'bulls', '公牛', 'United Center'),
('CHP', '芝加哥包装工', NULL, NULL, NULL, NULL, NULL),
('CHS', '芝加哥雄鹿', NULL, NULL, NULL, NULL, NULL),
('CHZ', '芝加哥西风', NULL, NULL, NULL, NULL, NULL),
('CIN', '辛辛纳提皇家', NULL, NULL, NULL, NULL, NULL),
('CLE', '克里夫兰骑士', 'eastern', 'central', 'cavaliers', '骑士', 'Quicken Loans Arena'),
('CLR', '克里夫兰反叛者', NULL, NULL, NULL, NULL, NULL),
('DAL', '达拉斯小牛', 'western', 'northwest', 'mavericks', '小牛', 'American Airlines Center'),
('DEN', '丹佛掘金', 'western', 'southwest', 'nuggets', '掘金', 'Pepsi Center'),
('DET', '底特律活塞', 'eastern', 'central', 'pistons', '活塞', 'The Palace of Auburn Hills'),
('DLC', '达拉斯丛林', NULL, NULL, NULL, NULL, NULL),
('DNA', '丹佛老老掘金', NULL, NULL, NULL, NULL, NULL),
('DNN', '丹佛老掘金', NULL, NULL, NULL, NULL, NULL),
('DNR', '丹佛火箭', NULL, NULL, NULL, NULL, NULL),
('DTF', '底特律猎鹰', NULL, NULL, NULL, NULL, NULL),
('FLO', '佛罗里达人', NULL, NULL, NULL, NULL, NULL),
('FTW', '福特韦恩活塞', NULL, NULL, NULL, NULL, NULL),
('GSW', '金州勇士', 'western', 'pacific', 'warriors', NULL, 'Oracle Arena'),
('HOU', '休斯顿火箭', 'western', 'northwest', 'rockets', '火箭', 'Toyota Center'),
('HSM', '休斯顿小牛', NULL, NULL, NULL, NULL, NULL),
('INA', '印第安纳老者', NULL, NULL, NULL, NULL, NULL),
('IND', '印第安纳步行者', 'eastern', 'central', 'pacers', '步行者', 'Bankers Life Fieldhouse'),
('INJ', '印第安纳喷气机', NULL, NULL, NULL, NULL, NULL),
('INO', '印第安纳波利斯', NULL, NULL, NULL, NULL, NULL),
('KCK', '堪萨斯城国王', NULL, NULL, NULL, NULL, NULL),
('KCO', '堪萨奥马哈国王', NULL, NULL, NULL, NULL, NULL),
('KEN', '肯塔基上校', NULL, NULL, NULL, NULL, NULL),
('LAC', '洛杉矶快船', 'western', 'pacific', 'clippers', NULL, 'STAPLES Center'),
('LAL', '洛杉矶湖人', 'western', 'pacific', 'lakers', '湖人', 'STAPLES Center'),
('LAS', '洛杉矶星光', NULL, NULL, NULL, NULL, NULL),
('MEM', '孟菲斯灰熊', 'western', 'northwest', 'grizzlies', '灰熊', 'FedEx Forum'),
('MIA', '迈阿密热火', 'eastern', 'southeast', 'heat', '热火', 'AmericanAirlines Arena'),
('MIL', '密尔沃基雄鹿', 'eastern', 'central', 'bucks', '雄鹿', 'BMO Harris Bradley Center'),
('MIN', '明尼苏达森林狼', 'western', 'southwest', 'timberwolves', '森林狼', 'Target Center'),
('MLH', '密尔沃基老鹰', NULL, NULL, NULL, NULL, NULL),
('MMF', '迈阿密佛罗里达', NULL, NULL, NULL, NULL, NULL),
('MMP', '孟菲斯职业者', NULL, NULL, NULL, NULL, NULL),
('MMS', '孟菲斯超音速', NULL, NULL, NULL, NULL, NULL),
('MMT', '孟菲斯檀木', NULL, NULL, NULL, NULL, NULL),
('MNL', '明尼阿波利斯湖人', NULL, NULL, NULL, NULL, NULL),
('MNM', '明尼苏达麝香', NULL, NULL, NULL, NULL, NULL),
('MNP', '明尼苏达风笛手', NULL, NULL, NULL, NULL, NULL),
('NJA', '新泽西美洲人', NULL, NULL, NULL, NULL, NULL),
('NJN', '新泽西篮网', NULL, NULL, NULL, '篮网', NULL),
('NOB', '新奥尔良海盗', NULL, NULL, NULL, NULL, NULL),
('NOH', '新奥尔良鹈鹕', 'western', 'northwest', NULL, '鹈鹕', NULL),
('NOJ', '新奥尔良爵士', NULL, NULL, NULL, NULL, NULL),
('NYA', '纽约老篮网', NULL, NULL, NULL, NULL, NULL),
('NYK', '纽约尼克斯', 'eastern', 'atlantic', 'knicks', '尼克斯', 'Madison Square Garden (IV)'),
('NYN', '纽约篮网', NULL, NULL, NULL, NULL, NULL),
('OAK', '奥克兰橡树', NULL, NULL, NULL, NULL, NULL),
('OKC', '俄克拉荷马雷霆', 'western', 'southwest', 'thunder', '雷霆', 'Chesapeake Energy Arena'),
('ORL', '奥兰多魔术', 'eastern', 'southeast', 'magic', '魔术', 'Amway Center'),
('PHI', '费城76人', 'eastern', 'atlantic', 'sixers', '76人', 'Wells Fargo Center'),
('PHO', '菲尼克斯太阳', 'western', 'pacific', NULL, '太阳', NULL),
('PHW', '费城勇士', NULL, NULL, NULL, NULL, NULL),
('PIT', '匹兹堡铁人', NULL, NULL, NULL, NULL, NULL),
('POR', '波特兰开拓者', 'western', 'southwest', 'blazers', '开拓者', 'Moda Center'),
('PRO', '普罗登斯蒸汽', NULL, NULL, NULL, NULL, NULL),
('PTC', '匹兹堡秃鹰', NULL, NULL, NULL, NULL, NULL),
('PTP', '匹兹堡风笛手', NULL, NULL, NULL, NULL, NULL),
('ROC', '罗切斯特皇家', NULL, NULL, NULL, NULL, NULL),
('SAA', '圣安东尼奥老刺', NULL, NULL, NULL, NULL, NULL),
('SAC', '萨克拉门托国王', 'western', 'pacific', 'kings', '国王', 'Sleep Train Arena'),
('SAS', '圣安东尼奥马刺', 'western', 'northwest', 'spurs', '马刺', 'AT&T Center'),
('SDA', '圣迭戈征服者', NULL, NULL, NULL, NULL, NULL),
('SDC', '圣地亚哥快船', NULL, NULL, NULL, '快船', NULL),
('SDR', '圣地亚哥火箭', NULL, NULL, NULL, NULL, NULL),
('SDS', '圣地亚哥远航', NULL, NULL, NULL, NULL, NULL),
('SEA', '西雅图超音速', NULL, NULL, NULL, '超音速', NULL),
('SFW', '旧金山勇士', NULL, NULL, NULL, NULL, NULL),
('SHE', '西伯根红人', NULL, NULL, NULL, NULL, NULL),
('SSL', '圣路易斯精神', NULL, NULL, NULL, NULL, NULL),
('STB', '圣路易轰炸机', NULL, NULL, NULL, NULL, NULL),
('STL', '圣路易斯老鹰', NULL, NULL, NULL, NULL, NULL),
('SYR', '西拉丘斯国民', NULL, NULL, NULL, NULL, NULL),
('TEX', '德州丛林', NULL, NULL, NULL, NULL, NULL),
('TOR', '多伦多猛龙', 'eastern', 'atlantic', 'raptors', '猛龙', 'Air Canada Centre'),
('TRH', '多伦多麝香', NULL, NULL, NULL, NULL, NULL),
('TRI', '三城黑鹰', NULL, NULL, NULL, NULL, NULL),
('UTA', '犹他爵士', 'western', 'southwest', 'jazz', '爵士', 'EnergySolutions Arena'),
('UTS', '犹他星光', NULL, NULL, NULL, NULL, NULL),
('VAN', '温哥华灰熊', NULL, NULL, NULL, NULL, NULL),
('VIR', '维吉尼亚大地主', NULL, NULL, NULL, NULL, NULL),
('WAS', '华盛顿奇才', 'eastern', 'southeast', 'wizards', '奇才', 'Verizon Center'),
('WAT', '滑铁卢老鹰', NULL, NULL, NULL, NULL, NULL),
('WSA', '华盛顿首都', NULL, NULL, NULL, NULL, NULL),
('WSB', '华盛顿子弹', NULL, NULL, NULL, '子弹', NULL),
('WSC', '华盛顿老首都', NULL, NULL, NULL, NULL, NULL);

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
