export function formatRating(movies) {
  console.log(movies);
  let length = movies.length;
  var i = 0;
  for (i = 0; i < length; i++) {
    movies[i].avgRating = Number(movies[i].avgRating.toFixed(1))
  }
}
