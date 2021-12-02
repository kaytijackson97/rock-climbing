import { render, screen } from '@testing-library/react';
import Welcome from './Welcome';

test('renders Rock Climbing Header', () => {
  render(<Welcome />);
  const linkElement = screen.getByText(/Rock Climbing/i);
  expect(linkElement).toBeInTheDocument();
});