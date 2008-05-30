package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exports extends GenASTNode {
  public exports(exportList exportList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exportList>("exportList", exportList)
    }, firstToken, lastToken);
  }
  public exports(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new exports(cloneProperties(),firstToken,lastToken);
  }
  public exportList getExportList() {
    return ((PropertyZeroOrOne<exportList>)getProperty("exportList")).getValue();
  }
}
