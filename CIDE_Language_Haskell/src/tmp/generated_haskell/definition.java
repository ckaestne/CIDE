package tmp.generated_haskell;

import cide.gast.IToken;
import cide.gast.Property;
import cide.gparser.Token;

public abstract class definition extends GenASTNode {
  protected definition(Property[] p, Token firstToken, Token lastToken) { super(p, firstToken, lastToken); }
  protected definition(Property[] p, IToken firstToken, IToken lastToken) { super(p, firstToken, lastToken); }
}
