# -*- coding:utf-8 -*-

import requests
import sys

import CsvHelper



def find_MatchItemlive(game_id,ishome):
    reload(sys)
    sys.setdefaultencoding('utf-8')
    # http://china.nba.com/wap/static/data/game/snapshot_0041400311.json
    aim = 'http://china.nba.com/wap/static/data/game/snapshot_' \
          + str(game_id) + '.json'

    game = requests.get(aim)
    game.encoding = 'utf-8'
    if game.status_code >= 400:
        print('no such game ' + str(game.status_code))
        return

    if(ishome == 1):
        events = game.json()['payload']['homeTeam']['gamePlayers']
        for x in range(0, len(events)):
            CsvHelper.dict_to_csv_stream(events[x]['profile'], False)
            CsvHelper.dict_to_csv_stream(events[x]['boxscore'], False)
            CsvHelper.dict_to_csv_stream(events[x]['statTotal'], False)
    else:
        events = game.json()['payload']['awayTeam']['gamePlayers']
        for x in range(0, len(events)):
            CsvHelper.dict_to_csv_stream(events[x]['profile'], False)
            CsvHelper.dict_to_csv_stream(events[x]['boxscore'], False)
            CsvHelper.dict_to_csv_stream(events[x]['statTotal'], False)
        
        
if __name__ == '__main__':
    ID = sys.argv[1]
    ishome = int(sys.argv[2])
    find_MatchItemlive(ID,ishome)

