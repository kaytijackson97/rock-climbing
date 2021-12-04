import { render, screen } from '@testing-library/react';
import MyClimbs from './MyClimbs';

test('renders My Climbs Header', () => {
  render(<MyClimbs />);
  const linkElement = screen.getByText(/My Climbs/i);
  expect(linkElement).toBeInTheDocument();
});