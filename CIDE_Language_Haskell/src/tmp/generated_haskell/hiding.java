package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class hiding extends GenASTNode {
  public hiding(ASTTextNode text352, exportList exportList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text352", text352),
      new PropertyZeroOrOne<exportList>("exportList", exportList)
    }, firstToken, lastToken);
  }
  public hiding(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new hiding(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText352() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text352")).getValue();
  }
  public exportList getExportList() {
    return ((PropertyZeroOrOne<exportList>)getProperty("exportList")).getValue();
  }
}
