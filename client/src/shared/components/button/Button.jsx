import "./Button.css"

export default function Button({ type, loading, text, loadingText }) {
	return (
		<button type={type} disabled={loading} className="button">
			{loading ? loadingText : text}
		</button>
	)
}
