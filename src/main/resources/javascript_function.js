function printProperties(obj) {
    print(obj)
    var total = 0;
    for (var prop in obj) {
        print("key: " + prop + " value: " + obj[prop]);
        total = total + obj[prop];
    }
    return total;
}