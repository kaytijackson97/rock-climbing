import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Rock Climbing Header', () => {
  render(<App />);
  const linkElement = screen.getByText(/Rock Climbing/i);
  expect(linkElement).toBeInTheDocument();
});
