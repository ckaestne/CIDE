package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwCase2 extends SwCase {
  public SwCase2(ASTStringNode identifier, SwCaseLabel swCaseLabel, ArrayList<MoreSwCaseLabel> moreSwCaseLabel, Sequence_CodeUnit_InBlock sequence_CodeUnit_InBlock1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<SwCaseLabel>("swCaseLabel", swCaseLabel),
      new PropertyZeroOrMore<MoreSwCaseLabel>("moreSwCaseLabel", moreSwCaseLabel),
      new PropertyOne<Sequence_CodeUnit_InBlock>("sequence_CodeUnit_InBlock1", sequence_CodeUnit_InBlock1)
    }, firstToken, lastToken);
  }
  public SwCase2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwCase2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public SwCaseLabel getSwCaseLabel() {
    return ((PropertyOne<SwCaseLabel>)getProperty("swCaseLabel")).getValue();
  }
  public ArrayList<MoreSwCaseLabel> getMoreSwCaseLabel() {
    return ((PropertyZeroOrMore<MoreSwCaseLabel>)getProperty("moreSwCaseLabel")).getValue();
  }
  public Sequence_CodeUnit_InBlock getSequence_CodeUnit_InBlock1() {
    return ((PropertyOne<Sequence_CodeUnit_InBlock>)getProperty("sequence_CodeUnit_InBlock1")).getValue();
  }
}
