package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varargslist3 extends varargslist {
  public varargslist3(ExtraKeywordList extraKeywordList2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExtraKeywordList>("extraKeywordList2", extraKeywordList2)
    }, firstToken, lastToken);
  }
  public varargslist3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varargslist3(cloneProperties(),firstToken,lastToken);
  }
  public ExtraKeywordList getExtraKeywordList2() {
    return ((PropertyOne<ExtraKeywordList>)getProperty("extraKeywordList2")).getValue();
  }
}
