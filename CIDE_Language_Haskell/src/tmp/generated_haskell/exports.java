package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exports extends GenASTNode {
  public exports(exportList exportList, ASTTextNode text351, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exportList>("exportList", exportList),
      new PropertyZeroOrOne<ASTTextNode>("text351", text351)
    }, firstToken, lastToken);
  }
  public exports(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exports(cloneProperties(),firstToken,lastToken);
  }
  public exportList getExportList() {
    return ((PropertyZeroOrOne<exportList>)getProperty("exportList")).getValue();
  }
  public ASTTextNode getText351() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text351")).getValue();
  }
}
