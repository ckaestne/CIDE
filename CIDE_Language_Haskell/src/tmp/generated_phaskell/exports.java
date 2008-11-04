package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exports extends GenASTNode {
  public exports(exportsList exportsList, ASTTextNode text364, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exportsList>("exportsList", exportsList),
      new PropertyZeroOrOne<ASTTextNode>("text364", text364)
    }, firstToken, lastToken);
  }
  public exports(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exports(cloneProperties(),firstToken,lastToken);
  }
  public exportsList getExportsList() {
    return ((PropertyZeroOrOne<exportsList>)getProperty("exportsList")).getValue();
  }
  public ASTTextNode getText364() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text364")).getValue();
  }
}
