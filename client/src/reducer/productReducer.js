const initState = {
    products: []
}

const productReducer = (state=initState, action) => {
    switch(action.type){
        case "FETCH_PRODUCTS":
            return {...state}
        default:
            return {...state}
    }
}

export default productReducer