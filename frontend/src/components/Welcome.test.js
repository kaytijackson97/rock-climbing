import { render, screen } from '@testing-library/react';
import Welcome from './Welcome';

describe('Welcome page', () => {
  test('renders Rock Climbing Header', () => {
    render(<Welcome />);
    const linkElement = screen.getByText(/Rock Climbing/i);
    expect(linkElement).toBeInTheDocument();
  });
}); 