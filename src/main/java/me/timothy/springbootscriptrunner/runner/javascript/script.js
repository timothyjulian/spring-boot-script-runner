function main(input) {
    let obj = JSON.parse(input);

    // if logic
    if (obj.age < 18) {
        obj.drink = false;
    } else {
        obj.drink = true;
    }

    // string manipulation
    obj.name = capitalizeFirstLetterOfEachWord(obj.name);

    // JSON manipulation
    let fullAddress = obj.addressDetail.street + " - House No " + obj.addressDetail.houseNo + " - Postal Code " + obj.addressDetail.postalCode;
    obj.additionalInfo = {
        fullAddress,
        job: obj.job
    }
    delete obj.job;
    delete obj.addressDetail;

    // putting null value
    obj.null = null;
    return JSON.stringify(obj);
}

function capitalizeFirstLetterOfEachWord(str) {
  return str.split(' ').map(word => {
    return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
  }).join(' ');
}

main(message);