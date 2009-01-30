package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varargslist1 extends varargslist {
  public varargslist1(defaultarg defaultarg, ArrayList<defaultarg> defaultarg1, ExtraArgList extraArgList, ExtraKeywordList extraKeywordList, ASTTextNode text578, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<defaultarg>("defaultarg", defaultarg),
      new PropertyZeroOrMore<defaultarg>("defaultarg1", defaultarg1),
      new PropertyZeroOrOne<ExtraArgList>("extraArgList", extraArgList),
      new PropertyZeroOrOne<ExtraKeywordList>("extraKeywordList", extraKeywordList),
      new PropertyZeroOrOne<ASTTextNode>("text578", text578)
    }, firstToken, lastToken);
  }
  public varargslist1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varargslist1(cloneProperties(),firstToken,lastToken);
  }
  public defaultarg getDefaultarg() {
    return ((PropertyOne<defaultarg>)getProperty("defaultarg")).getValue();
  }
  public ArrayList<defaultarg> getDefaultarg1() {
    return ((PropertyZeroOrMore<defaultarg>)getProperty("defaultarg1")).getValue();
  }
  public ExtraArgList getExtraArgList() {
    return ((PropertyZeroOrOne<ExtraArgList>)getProperty("extraArgList")).getValue();
  }
  public ExtraKeywordList getExtraKeywordList() {
    return ((PropertyZeroOrOne<ExtraKeywordList>)getProperty("extraKeywordList")).getValue();
  }
  public ASTTextNode getText578() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text578")).getValue();
  }
}
