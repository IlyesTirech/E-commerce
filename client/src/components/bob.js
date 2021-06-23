
const state = [];

const turnIntoNiceArrayThingPleaseAndThankYou = (data) => {

   for(let i = 0; i < 32; i++){
        let varName = data.eval(`compare-tabs_1_city_${i}_name`);
        let varAqi = data.eval(`compare-tabs_1_city_${i}_aqi`);
        let varCigg = data.eval(`compare-tabs_1_city_${i}_cigg`);
        let cityDetails = {
            name : varName,
            aqi: varAqi,
            cigg: varCigg
        }
        console.log(cityDetails)
    }
}