# fp-grunndata

Stateless backend serving grunndata, primarily stonadskonto calculations, to self-service clients.

## Shared context

- Source of truth for shared domain, architecture, and conventions: `navikt/fp-context`
- Copilot Space: `navikt/TeamForeldrepenger`

## Repo-specific context

| Topic       | Details                                                           |
|-------------|-------------------------------------------------------------------|
| Role        | Exposes uttak and kontoberegning data to selvbetjening frontends  |
| Consumers   | `foreldrepengesoknad`                                             |
| Tech stack  | Standard fp Java backend; stateless; no authentication;           |

Accepts requests from pre-authorized applications (naiserator); no further authentication or authorization.

Supports type generation for frontend consumers using local and dev deployments.

## Entry points

- `UttakRestTjeneste`: calculates quota configuration and available days using `fp-stonadskonto`

## Verification

- Verify changes through consuming frontend or backend flows rather than direct `fp-autotest` coverage.
