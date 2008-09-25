package tmp.generated_haskell;

import cide.gast.IToken;
import cide.gast.Property;
import cide.gparser.Token;

public abstract class declaration extends GenASTNode {
  protected declaration(Property[] p, Token firstToken, Token lastToken) { super(p, firstToken, lastToken); }
  protected declaration(Property[] p, IToken firstToken, IToken lastToken) { super(p, firstToken, lastToken); }
}
