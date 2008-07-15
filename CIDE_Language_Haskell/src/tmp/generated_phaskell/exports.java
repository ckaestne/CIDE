package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exports extends GenASTNode {
  public exports(exportsList exportsList, ASTTextNode text363, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exportsList>("exportsList", exportsList),
      new PropertyZeroOrOne<ASTTextNode>("text363", text363)
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
  public ASTTextNode getText363() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text363")).getValue();
  }
}
