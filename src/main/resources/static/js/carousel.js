const track = document.querySelector('.carousel-track');
const members = document.querySelectorAll('.team-member');
const prevBtn = document.querySelector('.prev-btn');
const nextBtn = document.querySelector('.next-btn');

const itemWidth = members[0].offsetWidth; // 300px
const gap = 20; // el mismo que usaste en CSS
let index = 0;

// calcula el número máximo de desplazamientos posibles
const maxIndex = members.length - 3; // 3 visibles a la vez

function updatePosition() {
  const offset = index * (itemWidth + gap);
  track.style.transform = `translateX(-${offset}px)`;
}

nextBtn.addEventListener('click', () => {
  if (index < maxIndex) {
    index++;
    updatePosition();
  }
});

prevBtn.addEventListener('click', () => {
  if (index > 0) {
    index--;
    updatePosition();
  }
});
