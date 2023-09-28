#import excel2json

#df = excel2json.convert_from_file('TrainingScheduleDefinitionscopy.xls')

#print(df)

# Path: generateJson.py
import pandas as pd
import json
import pprint as pp

# read the excel file
df = pd.read_excel('TrainingScheduleDefinitionscopy2.xlsx')       
# convert to json
json_records = df.to_json(orient='records')


with open('json_UNformatted_str.json', 'w') as f:
    f.write(json_records)


#print(json_records)

#json_formatted_str = json.dumps(json_records, indent=4)
json_formatted_str = json.loads(json_records)
#print(json_formatted_str)
pp.pprint(json_formatted_str)

json_formatted_str2 = json.dumps(json_formatted_str, indent=4)
with open('json_formatted_str.json', 'w') as f:
    f.write(json_formatted_str2)


# write to a file
#with open('TrainingScheduleDefinitionscopy.json', 'w') as f:
#    f.write(json_records)

