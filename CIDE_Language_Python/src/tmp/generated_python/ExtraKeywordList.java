package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExtraKeywordList extends GenASTNode {
  public ExtraKeywordList(power power, Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<power>("power", power),
      new PropertyOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public ExtraKeywordList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExtraKeywordList(cloneProperties(),firstToken,lastToken);
  }
  public power getPower() {
    return ((PropertyOne<power>)getProperty("power")).getValue();
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
}
