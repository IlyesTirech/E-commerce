const initState = {};

export default function (state = initState, action) {
  const { type, payload } = action;

  switch (type) {
    case "SET_MESSAGE":
      return { message: payload };

    case "CLEAR_MESSAGE":
      return { message: "" };

    default:
      return state;
  }
}