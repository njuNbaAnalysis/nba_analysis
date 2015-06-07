-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-06-07 15:23:01
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
('ANA', '阿纳海姆搞基', '', '', '', '', ''),
('AND', '安德逊包装工', '', '', '', '', ''),
('ATL', '亚特兰大老鹰', 'eastern', 'southeast', 'hawks', '老鹰', 'Philips Arena'),
('BAL', '巴提摩尔子弹', '', '', '', '', ''),
('BKN', '布鲁克林篮网', 'eastern', 'atlantic', 'nets', '篮网', 'Barclays Center'),
('BLB', '巴尔的摩子弹', '', '', '', '', ''),
('BOS', '波士顿凯尔特人', 'eastern', 'atlantic', 'celtics', '凯尔特', 'TD Garden'),
('BUF', '布法罗勇敢者', '', '', '', '', ''),
('CAP', '首都子弹', '', '', '', '', ''),
('CAR', '卡罗来纳美洲狮', '', '', '', '', ''),
('CHA', '夏洛特黄蜂', 'eastern', 'southeast', 'hornets', '黄蜂', 'Time Warner Cable Arena'),
('CHH', '夏洛特黄蜂', '', '', '', '黄蜂', ''),
('CHI', '芝加哥公牛', 'eastern', 'central', 'bulls', '公牛', 'United Center'),
('CHP', '芝加哥包装工', '', '', '', '', ''),
('CHS', '芝加哥雄鹿', '', '', '', '', ''),
('CHZ', '芝加哥西风', '', '', '', '', ''),
('CIN', '辛辛纳提皇家', '', '', '', '', ''),
('CLE', '克里夫兰骑士', 'eastern', 'central', 'cavaliers', '骑士', 'Quicken Loans Arena'),
('CLR', '克里夫兰反叛者', '', '', '', '', ''),
('DAL', '达拉斯小牛', 'western', 'northwest', 'mavericks', '小牛', 'American Airlines Center'),
('DEN', '丹佛掘金', 'western', 'southwest', 'nuggets', '掘金', 'Pepsi Center'),
('DET', '底特律活塞', 'eastern', 'central', 'pistons', '活塞', 'The Palace of Auburn Hills'),
('DLC', '达拉斯丛林', '', '', '', '', ''),
('DNA', '丹佛老老掘金', '', '', '', '', ''),
('DNN', '丹佛老掘金', '', '', '', '', ''),
('DNR', '丹佛火箭', '', '', '', '', ''),
('DTF', '底特律猎鹰', '', '', '', '', ''),
('FLO', '佛罗里达人', '', '', '', '', ''),
('FTW', '福特韦恩活塞', '', '', '', '', ''),
('GSW', '金州勇士', 'western', 'pacific', 'warriors', '勇士', 'Oracle Arena'),
('HOU', '休斯顿火箭', 'western', 'northwest', 'rockets', '火箭', 'Toyota Center'),
('HSM', '休斯顿小牛', '', '', '', '', ''),
('INA', '印第安纳老者', '', '', '', '', ''),
('IND', '印第安纳步行者', 'eastern', 'central', 'pacers', '步行者', 'Bankers Life Fieldhouse'),
('INJ', '印第安纳喷气机', '', '', '', '', ''),
('INO', '印第安纳波利斯', '', '', '', '', ''),
('KCK', '堪萨斯城国王', '', '', '', '', ''),
('KCO', '堪萨奥马哈国王', '', '', '', '', ''),
('KEN', '肯塔基上校', '', '', '', '', ''),
('LAC', '洛杉矶快船', 'western', 'pacific', 'clippers', '', 'STAPLES Center'),
('LAL', '洛杉矶湖人', 'western', 'pacific', 'lakers', '湖人', 'STAPLES Center'),
('LAS', '洛杉矶星光', '', '', '', '', ''),
('MEM', '孟菲斯灰熊', 'western', 'northwest', 'grizzlies', '灰熊', 'FedEx Forum'),
('MIA', '迈阿密热火', 'eastern', 'southeast', 'heat', '热火', 'AmericanAirlines Arena'),
('MIL', '密尔沃基雄鹿', 'eastern', 'central', 'bucks', '雄鹿', 'BMO Harris Bradley Center'),
('MIN', '明尼苏达森林狼', 'western', 'southwest', 'timberwolves', '森林狼', 'Target Center'),
('MLH', '密尔沃基老鹰', '', '', '', '', ''),
('MMF', '迈阿密佛罗里达', '', '', '', '', ''),
('MMP', '孟菲斯职业者', '', '', '', '', ''),
('MMS', '孟菲斯超音速', '', '', '', '', ''),
('MMT', '孟菲斯檀木', '', '', '', '', ''),
('MNL', '明尼阿波利斯湖人', '', '', '', '', ''),
('MNM', '明尼苏达麝香', '', '', '', '', ''),
('MNP', '明尼苏达风笛手', '', '', '', '', ''),
('NJA', '新泽西美洲人', '', '', '', '', ''),
('NJN', '新泽西篮网', '', '', '', '篮网', ''),
('NOB', '新奥尔良海盗', '', '', '', '', ''),
('NOH', '新奥尔良鹈鹕', 'western', 'northwest', '', '鹈鹕', ''),
('NOJ', '新奥尔良爵士', '', '', '', '', ''),
('NYA', '纽约老篮网', '', '', '', '', ''),
('NYK', '纽约尼克斯', 'eastern', 'atlantic', 'knicks', '尼克斯', 'Madison Square Garden (IV)'),
('NYN', '纽约篮网', '', '', '', '', ''),
('OAK', '奥克兰橡树', '', '', '', '', ''),
('OKC', '俄克拉荷马雷霆', 'western', 'southwest', 'thunder', '雷霆', 'Chesapeake Energy Arena'),
('ORL', '奥兰多魔术', 'eastern', 'southeast', 'magic', '魔术', 'Amway Center'),
('PHI', '费城76人', 'eastern', 'atlantic', 'sixers', '76人', 'Wells Fargo Center'),
('PHO', '菲尼克斯太阳', 'western', 'pacific', '', '太阳', ''),
('PHW', '费城勇士', '', '', '', '', ''),
('PIT', '匹兹堡铁人', '', '', '', '', ''),
('POR', '波特兰开拓者', 'western', 'southwest', 'blazers', '开拓者', 'Moda Center'),
('PRO', '普罗登斯蒸汽', '', '', '', '', ''),
('PTC', '匹兹堡秃鹰', '', '', '', '', ''),
('PTP', '匹兹堡风笛手', '', '', '', '', ''),
('ROC', '罗切斯特皇家', '', '', '', '', ''),
('SAA', '圣安东尼奥老刺', '', '', '', '', ''),
('SAC', '萨克拉门托国王', 'western', 'pacific', 'kings', '国王', 'Sleep Train Arena'),
('SAS', '圣安东尼奥马刺', 'western', 'northwest', 'spurs', '马刺', 'AT&T Center'),
('SDA', '圣迭戈征服者', '', '', '', '', ''),
('SDC', '圣地亚哥快船', '', '', '', '快船', ''),
('SDR', '圣地亚哥火箭', '', '', '', '', ''),
('SDS', '圣地亚哥远航', '', '', '', '', ''),
('SEA', '西雅图超音速', '', '', '', '超音速', ''),
('SFW', '旧金山勇士', '', '', '', '', ''),
('SHE', '西伯根红人', '', '', '', '', ''),
('SSL', '圣路易斯精神', '', '', '', '', ''),
('STB', '圣路易轰炸机', '', '', '', '', ''),
('STL', '圣路易斯老鹰', '', '', '', '', ''),
('SYR', '西拉丘斯国民', '', '', '', '', ''),
('TEX', '德州丛林', '', '', '', '', ''),
('TOR', '多伦多猛龙', 'eastern', 'atlantic', 'raptors', '猛龙', 'Air Canada Centre'),
('TRH', '多伦多麝香', '', '', '', '', ''),
('TRI', '三城黑鹰', '', '', '', '', ''),
('UTA', '犹他爵士', 'western', 'southwest', 'jazz', '爵士', 'EnergySolutions Arena'),
('UTS', '犹他星光', '', '', '', '', ''),
('VAN', '温哥华灰熊', '', '', '', '', ''),
('VIR', '维吉尼亚大地主', '', '', '', '', ''),
('WAS', '华盛顿奇才', 'eastern', 'southeast', 'wizards', '奇才', 'Verizon Center'),
('WAT', '滑铁卢老鹰', '', '', '', '', ''),
('WSA', '华盛顿首都', '', '', '', '', ''),
('WSB', '华盛顿子弹', '', '', '', '子弹', ''),
('WSC', '华盛顿老首都', '', '', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `teamnamelist`
--
ALTER TABLE `teamnamelist`
  ADD PRIMARY KEY (`teamNameEn`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
