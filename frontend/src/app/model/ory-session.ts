export interface OrySession {
  id: string;
  active: boolean;
  expires_at: string;

  identity: {
    id: string;
    state: string;
    traits: {
      email: string;
      name?: {
        first?: string;
        last?: string;
      };
    };
  };
}
