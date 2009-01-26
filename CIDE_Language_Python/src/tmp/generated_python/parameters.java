package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class parameters extends GenASTNode {
  public parameters(varargslist varargslist, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<varargslist>("varargslist", varargslist)
    }, firstToken, lastToken);
  }
  public parameters(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new parameters(cloneProperties(),firstToken,lastToken);
  }
  public varargslist getVarargslist() {
    return ((PropertyZeroOrOne<varargslist>)getProperty("varargslist")).getValue();
  }
}
