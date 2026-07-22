import "./AuthButton.css"

export default function AuthButton({ loading, text, loadingText }) {
	return (
		<button type="submit" disabled={loading} className="auth-button">
			{loading ? loadingText : text}
		</button>
	)
}
