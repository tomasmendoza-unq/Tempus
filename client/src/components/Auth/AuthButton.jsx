export default function AuthButton({ loading, text, loadingText }) {
  return (
    <button
      type="submit"
      disabled={loading}
      className="w-full bg-red-950 text-white py-2 rounded font-bold hover:bg-red-900 disabled:bg-gray-400 transition-all"
    >
      {loading ? loadingText : text}
    </button>
  );
}