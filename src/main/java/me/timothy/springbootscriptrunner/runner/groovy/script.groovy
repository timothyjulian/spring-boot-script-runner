import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def main(String input) {
    def jsonSlurper = new JsonSlurper()
    def obj = jsonSlurper.parseText(input)

    // if logic
    if (obj['age'] < 18) {
        obj['drink'] = false
    } else {
        obj['drink'] = true
    }

    // string manipulation
    obj['name'] = capitalizeFirstLetterOfEachWord(obj.name)

    // JSON manipulation
    def fullAddress = "${obj['addressDetail']['street']} - House No ${obj['addressDetail']['houseNo']} - Postal Code ${obj['addressDetail']['postalCode']}"
    obj['additionalInfo'] = [
            fullAddress: fullAddress,
            job: obj['job']
    ]
    obj.remove('job')
    obj.remove('addressDetail')

    // putting null value
    obj['null'] = null

    return JsonOutput.toJson(obj)
}

def capitalizeFirstLetterOfEachWord(String str) {
    return str.split(' ').collect { word ->
        word[0].toUpperCase() + word[1..-1].toLowerCase()
    }.join(' ')
}

return main(message)