package tmp.generated_haskell;

import cide.gast.ASTTextNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class importDecl extends GenASTNode {
  public importDecl(ASTTextNode text2, naam naam, naam naam1, hiding hiding, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text2", text2),
      new PropertyOne<naam>("naam", naam),
      new PropertyZeroOrOne<naam>("naam1", naam1),
      new PropertyZeroOrOne<hiding>("hiding", hiding)
    }, firstToken, lastToken);
  }
  public importDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new importDecl(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText2() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text2")).getValue();
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public naam getNaam1() {
    return ((PropertyZeroOrOne<naam>)getProperty("naam1")).getValue();
  }
  public hiding getHiding() {
    return ((PropertyZeroOrOne<hiding>)getProperty("hiding")).getValue();
  }
}
