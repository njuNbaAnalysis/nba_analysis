# -*- coding:utf-8 -*-
import reload

import requests
import sys

import CsvHelper

__author__ = 'zzt'


def find_live(game_id, period):
    # http://china.nba.com/wap/static/data/game/playbyplay_0041400311_4.json
    aim = 'http://china.nba.com/wap/static/data/game/playbyplay_' \
          + str(game_id) + \
          '_' \
          + str(period) + \
          '.json'

    game = requests.get(aim)
    game.encoding = 'utf-8'
    if game.status_code >= 400:
        print('no such game ' + str(game.status_code))
        return

    events = game.json()['payload']['playByPlays'][0]['events']
    CsvHelper.dict_to_csv_stream(events[0])
    for x in range(1, len(events)):
        CsvHelper.dict_to_csv_stream(
        events[x], False
        )
            #print events[x]
        
if __name__ == '__main__':
    #playoff_date_code = '004'
    #season = 14
    #round_of = '{:03}'.format(3)
    #rank = 0
    #kth = 1
    #ID = playoff_date_code + str(season) + round_of + str(rank) + str(kth)
    ID = sys.argv[1]
    period = int(sys.argv[2])
    find_live(ID , period)

