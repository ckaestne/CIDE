package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varargslist2 extends varargslist {
  public varargslist2(ExtraArgList extraArgList1, ExtraKeywordList extraKeywordList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ExtraArgList>("extraArgList1", extraArgList1),
      new PropertyZeroOrOne<ExtraKeywordList>("extraKeywordList1", extraKeywordList1)
    }, firstToken, lastToken);
  }
  public varargslist2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varargslist2(cloneProperties(),firstToken,lastToken);
  }
  public ExtraArgList getExtraArgList1() {
    return ((PropertyOne<ExtraArgList>)getProperty("extraArgList1")).getValue();
  }
  public ExtraKeywordList getExtraKeywordList1() {
    return ((PropertyZeroOrOne<ExtraKeywordList>)getProperty("extraKeywordList1")).getValue();
  }
}
