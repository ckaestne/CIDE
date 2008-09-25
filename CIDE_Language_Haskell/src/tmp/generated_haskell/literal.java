package tmp.generated_haskell;

import cide.gast.IToken;
import cide.gast.Property;
import cide.gparser.Token;

public abstract class literal extends GenASTNode {
  protected literal(Property[] p, Token firstToken, Token lastToken) { super(p, firstToken, lastToken); }
  protected literal(Property[] p, IToken firstToken, IToken lastToken) { super(p, firstToken, lastToken); }
}
