# -*- coding:utf-8 -*-
import reload

import requests
import sys

import CsvHelper

__author__ = 'zzt'


def find_MatchItemlive(game_id):
    # http://china.nba.com/wap/static/data/game/snapshot_0041400311.json
    aim = 'http://china.nba.com/wap/static/data/game/snapshot_' \
          + str(game_id) + '.json'
    
    game = requests.get(aim)
    game.encoding = 'utf-8'
    if game.status_code >= 400:
        print('no such game ' + str(game.status_code))
        return

    events = game.json()['payload']['homeTeam']['score']
    CsvHelper.dict_to_csv_stream(events, False)

    events = game.json()['payload']['awayTeam']['score']
    CsvHelper.dict_to_csv_stream(events, False)
    
        
if __name__ == '__main__':
    ID = sys.argv[1]
    find_MatchItemlive(ID)

