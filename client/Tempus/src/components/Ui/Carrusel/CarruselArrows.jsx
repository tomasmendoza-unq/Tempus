export default function CarruselArrows({ onPrev, onNext }) {
  return (
    <>
      <button
        type="button"
        onClick={onPrev}
        aria-label="Slide anterior"
        className="absolute left-3 top-1/2 z-10 -translate-y-1/2 rounded-full bg-slate-900/70 px-3 py-2 text-xl font-bold text-white transition hover:bg-slate-800"
      >
        &#8249;
      </button>

      <button
        type="button"
        onClick={onNext}
        aria-label="Slide siguiente"
        className="absolute right-3 top-1/2 z-10 -translate-y-1/2 rounded-full bg-slate-900/70 px-3 py-2 text-xl font-bold text-white transition hover:bg-slate-800"
      >
        &#8250;
      </button>
    </>
  )
}
