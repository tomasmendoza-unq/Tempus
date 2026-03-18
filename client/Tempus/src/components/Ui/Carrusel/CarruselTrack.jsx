import CarruselSlide from "./CarruselSlide"

export default function CarruselTrack({ slides, activeIndex }) {
  return (
    <div className="relative overflow-hidden rounded-2xl border border-slate-700/70 shadow-xl shadow-black/20">
      <div
        className="flex transition-transform duration-700 ease-in-out"
        style={{ transform: `translateX(-${activeIndex * 100}%)` }}
      >
        {slides.map((slide) => (
          <CarruselSlide key={slide.id} slide={slide} />
        ))}
      </div>
    </div>
  )
}
