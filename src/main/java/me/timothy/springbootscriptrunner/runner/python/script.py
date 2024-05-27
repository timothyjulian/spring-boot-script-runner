import json

def capitalize_first_letter_of_each_word(s):
    return ' '.join(word.capitalize() for word in s.split(' '))

def main(input):
    obj = json.loads(input)

    # if logic
    if obj['age'] < 18:
        obj['drink'] = False
    else:
        obj['drink'] = True

    # string manipulation
    obj['name'] = capitalize_first_letter_of_each_word(obj['name'])

    # JSON manipulation
    full_address = f"{obj['addressDetail']['street']} - House No {obj['addressDetail']['houseNo']} - Postal Code {obj['addressDetail']['postalCode']}"
    obj['additionalInfo'] = {
        'fullAddress': full_address,
        'job': obj['job']
    }
    del obj['job']
    del obj['addressDetail']

    # putting null value
    obj['null'] = None
    return json.dumps(obj)

main(message)