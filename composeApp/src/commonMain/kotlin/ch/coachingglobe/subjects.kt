package ch.coachingglobe

val user = UserDto(
    id = 23,
    firstName = "Max",
    lastName = "Mustermann",
    photoUrl = null,
    email = ""
)

val author = Author(
    user = user,
    descr = "I am passionate about coaching and personal development.",
    profession = "Coach"
)

val subjectExamples = Subject(
    id_legacy = "coaching_globe",
    title = "CoachingGlobe",
    description = "A holistic coaching framework combining personal development, spirituality, relationships, and global impact. It integrates key practices to empower individuals in multiple aspects of their lives.",
    subjects = listOf(
        Subject(
            id_legacy = "ich_mit_mir",
            title = "Ich mit mir",
            description = "Focuses on self-awareness, personal growth, and emotional well-being.",
            coachableSets = listOf(
                CoachableSet(
                    author = author,
                    ic_legacy = "mobil_set",
                    title = "MOBIL",
                    description = "Enhance focus and well-being through movement and energy.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "mobil_01",
                            title = "Movement and Energy",
                            description = "Engage in physical exercises that boost energy."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "persoenlichkeitsstest_set",
                    title = "Persönlichkeitsstest",
                    description = "Using personality tests to understand your behavior and preferences.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "persoenlichkeits_test_01",
                            title = "Understand Your Personality",
                            description = "Take a personality test and reflect on your results."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "disg_set",
                    title = "DISG",
                    description = "A framework for understanding behavioral styles and improving interactions.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "disg_01",
                            title = "Behavioral Styles",
                            description = "Understand the four primary behavior types: Dominance, Influence, Steadiness, and Conscientiousness."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "anteile_innens_team_set",
                    title = "Anteile – inneres Team",
                    description = "Exploring the different aspects or 'parts' of yourself that influence thoughts and actions.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "anteile_innens_team_01",
                            title = "Inner Team Reflection",
                            description = "Identify and reflect on the different internal voices and perspectives that guide you."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "zeit_management_set",
                    title = "Zeit Management",
                    description = "Effective time management techniques to prioritize tasks and increase productivity.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "zeit_management_01",
                            title = "Time Prioritization",
                            description = "Learn techniques to organize your daily tasks based on importance and urgency."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "box_atmung_set",
                    title = "Box Atmung",
                    description = "Breathing techniques for relaxation and stress management.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "box_atmung_01",
                            title = "Breathing Exercise",
                            description = "Practice box breathing to reduce stress and calm the mind."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "fuenf_vier_drei_zwei_eins_uebung_set",
                    title = "5-4-3-2-1 Übung",
                    description = "A grounding exercise to bring awareness to the present moment.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "fuenf_vier_drei_zwei_eins_uebung_01",
                            title = "5-4-3-2-1 Grounding",
                            description = "Use your five senses to reconnect with your environment and stay present."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "achtsamkeit_set",
                    title = "Achtsamkeit",
                    description = "Mindfulness practice to improve awareness and reduce stress.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "achtsamkeit_01",
                            title = "Mindful Awareness",
                            description = "Practice being fully present in the moment and aware of your thoughts, feelings, and surroundings."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "emotion_set",
                    title = "Emotion",
                    description = "Understanding and managing emotions to achieve emotional stability.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "emotion_01",
                            title = "Emotion Regulation",
                            description = "Learn strategies for identifying and managing your emotional responses."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "beduerfnisse_set",
                    title = "Bedürfnisse",
                    description = "Recognizing and addressing personal needs for well-being.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "beduerfnisse_01",
                            title = "Identify Needs",
                            description = "Reflect on your emotional and physical needs to support your growth."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "selbstregulation_set",
                    title = "Selbstregulation",
                    description = "Learning to regulate one's emotional responses and behaviors.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "selbstregulation_01",
                            title = "Self-Regulation Techniques",
                            description = "Practice techniques to regulate emotional responses and maintain balance."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gedanken_set",
                    title = "Gedanken",
                    description = "Managing and reshaping thoughts to create a positive mindset.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gedanken_01",
                            title = "Thought Reshaping",
                            description = "Learn to identify and reframe negative thought patterns."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gewohnheiten_set",
                    title = "Gewohnheiten",
                    description = "Developing habits that foster personal growth and well-being.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gewohnheiten_01",
                            title = "Habit Formation",
                            description = "Implement small changes to build habits that align with your goals."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gehalte_und_beduerfnisse_set",
                    title = "Gehalte und Bedürfnisse",
                    description = "Identifying and addressing the underlying emotional states and needs.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gehalte_und_beduerfnisse_01",
                            title = "Understanding Needs",
                            description = "Acknowledge your emotional needs and how they influence your actions."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "stress_framing_set",
                    title = "Stress Framing",
                    description = "Techniques to reframe stressful situations and reduce their negative impact.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "stress_framing_01",
                            title = "Stress Reframing",
                            description = "Practice reframing stressful situations to see them in a more manageable light."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "inneres_kind_set",
                    title = "Inneres Kind",
                    description = "Connecting with the 'inner child' to heal past emotional wounds.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "inneres_kind_01",
                            title = "Inner Child Reflection",
                            description = "Identify childhood wounds and explore healing techniques."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "nicht_genug_set",
                    title = "Nicht genug",
                    description = "Overcoming feelings of inadequacy and fostering self-acceptance.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "nicht_genug_01",
                            title = "Overcoming Self-Doubt",
                            description = "Address feelings of inadequacy by reinforcing your self-worth."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "lebenslinien_enttarnen_set",
                    title = "Lebenslinien enttarnen",
                    description = "Identifying recurring life patterns to understand oneself better.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "lebenslinien_enttarnen_01",
                            title = "Life Pattern Reflection",
                            description = "Reflect on your life journey to identify repeating patterns and their meanings."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "wenn_erstmal_dann_falle_set",
                    title = "„Wenn erstmal dann ...“ Falle",
                    description = "Recognizing common cognitive biases and traps that hold us back.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "wenn_erstmal_dann_falle_01",
                            title = "Cognitive Bias Awareness",
                            description = "Identify and challenge 'if-then' traps and limiting beliefs."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "sicherer_ort_set",
                    title = "Sicherer Ort",
                    description = "Creating a safe mental space to foster inner peace and resilience.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "sicherer_ort_01",
                            title = "Safe Mental Space",
                            description = "Learn to create a mental sanctuary for relaxation and stress relief."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "vertrauen_oder_kontrolle_set",
                    title = "Vertrauen oder Kontrolle",
                    description = "Balancing trust and control to reduce anxiety and increase self-confidence.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "vertrauen_oder_kontrolle_01",
                            title = "Trust vs Control",
                            description = "Reflect on the balance between trust and control in your life."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "self_set",
                    title = "Self",
                    description = "Understanding your true self, your values, and your authentic desires.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "self_01",
                            title = "Authentic Self",
                            description = "Explore your true self and align your actions with your core values."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "medien_hygiene_set",
                    title = "Medien Hygiene",
                    description = "Maintaining a healthy relationship with media consumption to protect mental health.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "medien_hygiene_01",
                            title = "Healthy Media Consumption",
                            description = "Limit media consumption to improve focus and mental well-being."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "anspannung_und_regeneration_set",
                    title = "Anspannung und Regeneration",
                    description = "Balancing periods of tension with relaxation to prevent burnout.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "anspannung_und_regeneration_01",
                            title = "Balance Stress and Relaxation",
                            description = "Practice techniques to relieve tension and encourage relaxation."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "selbststrukturen_set",
                    title = "Selbststrukturen",
                    description = "Creating personal structures that support long-term growth and resilience.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "selbststrukturen_01",
                            title = "Create Supporting Structures",
                            description = "Implement daily habits and routines to promote personal growth."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "festeigungen_set",
                    title = "Festeigungen",
                    description = "Recognizing and celebrating personal milestones and achievements.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "festeigungen_01",
                            title = "Celebrate Achievements",
                            description = "Reflect on and celebrate your accomplishments to reinforce positive behavior."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "duerfen_statt_muessen_set",
                    title = "Dürfen statt müssen",
                    description = "Shifting from a mindset of obligation to one of choice and permission.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "duerfen_statt_muessen_01",
                            title = "Shift Mindset",
                            description = "Adopt a mindset of choice rather than obligation in your actions."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "selbstvertrauen_set",
                    title = "Selbstvertrauen",
                    description = "Building self-confidence through positive reinforcement and self-belief.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "selbstvertrauen_01",
                            title = "Boost Self-Confidence",
                            description = "Use positive affirmations and behaviors to strengthen your self-belief."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "dankbarkeit_routinen_set",
                    title = "Dankbarkeit Routinen",
                    description = "Gratitude practices that enhance positivity and happiness.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "dankbarkeit_routinen_01",
                            title = "Gratitude Practice",
                            description = "Incorporate daily gratitude exercises to shift focus to positivity."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "hilfreiche_und_blockierende_visualisierungen_set",
                    title = "Hilfreiche und blockierende Visualisierungen",
                    description = "Visualization techniques to overcome obstacles and boost success.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "hilfreiche_und_blockierende_visualisierungen_01",
                            title = "Visualization for Success",
                            description = "Practice visualization to clear mental blocks and reinforce success."
                        )
                    )
                )
            )
        ),
        Subject(
            id_legacy = "ich_mit_gott",
            title = "Ich mit Gott",
            description = "Focuses on strengthening spiritual connection and purpose through practices like prayer and reflection.",
            coachableSets = listOf(
                CoachableSet(
                    author = author,
                    ic_legacy = "gott_kennenlernen_set",
                    title = "Gott kennenlernen",
                    description = "Deepening the relationship with God through introspection and spiritual exercises.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gott_kennenlernen_01",
                            title = "Explore Your Spirituality",
                            description = "Reflect on your spiritual journey and connection with God."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "bibel_lesen_set",
                    title = "Bibel lesen",
                    description = "Reading and reflecting on biblical texts to guide personal growth.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "bibel_lesen_01",
                            title = "Read and Reflect on Scripture",
                            description = "Engage with the Bible to gain spiritual insight and guidance."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "beten_set",
                    title = "Beten",
                    description = "Establishing a consistent prayer practice to communicate with the divine.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "beten_01",
                            title = "Prayer Practice",
                            description = "Start a daily prayer practice to build a deeper connection with God."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "meinen_auftrag_erkennen_set",
                    title = "Meinen Auftrag erkennen",
                    description = "Discovering one's spiritual calling and life's purpose.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "meinen_auftrag_erkennen_01",
                            title = "Recognize Your Purpose",
                            description = "Reflect on your personal mission and how it aligns with divine will."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "religioeses_leistungsdenken_set",
                    title = "Religiöse Leistungsdenken",
                    description = "Dealing with performance-based spirituality and embracing grace.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "religioeses_leistungsdenken_01",
                            title = "Grace vs. Performance",
                            description = "Shift from performance-based spirituality to a focus on grace and divine love."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "schuld_und_vergebung_set",
                    title = "Schuld und Vergebung",
                    description = "Understanding the spiritual concepts of sin, guilt, and forgiveness.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "schuld_und_vergebung_01",
                            title = "Guilt and Forgiveness",
                            description = "Explore the process of forgiveness and healing from guilt in your spiritual life."
                        )
                    )
                )
            )
        ),
        Subject(
            id_legacy = "ich_mit_anderen",
            title = "Ich mit anderen",
            description = "Focuses on building emotional intelligence and healthy relationships in personal and professional environments.",
            coachableSets = listOf(
                CoachableSet(
                    author = author,
                    ic_legacy = "konfliktfaehigkeit_set",
                    title = "Konfliktfähigkeit",
                    description = "Develop conflict resolution skills to maintain peaceful relationships.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "konfliktfaehigkeit_01",
                            title = "Resolve Conflicts Effectively",
                            description = "Learn strategies to manage and resolve conflicts."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "soziale_gesundheit_set",
                    title = "Soziale Gesundheit",
                    description = "Fostering social well-being through supportive relationships.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "soziale_gesundheit_01",
                            title = "Build Healthy Relationships",
                            description = "Cultivate meaningful and supportive relationships in your personal and professional life."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "vergebungsbereitschaft_set",
                    title = "Vergebungsbereitschaft",
                    description = "Learning the power of forgiveness and its role in healing relationships.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "vergebungsbereitschaft_01",
                            title = "Practice Forgiveness",
                            description = "Understand the importance of forgiveness and begin incorporating it into your relationships."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "authentisch_sein_set",
                    title = "Authentisch sein",
                    description = "Being true to yourself in all interactions and relationships.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "authentisch_sein_01",
                            title = "Authenticity in Relationships",
                            description = "Learn to express yourself authentically and build stronger connections."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "empathie_fuer_andere_set",
                    title = "Empathie für andere",
                    description = "Developing empathy to understand and relate to others' emotions.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "empathie_fuer_andere_01",
                            title = "Cultivate Empathy",
                            description = "Develop your ability to empathize with others and improve your interpersonal relationships."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gemeinschaften_gruenden_set",
                    title = "Gemeinschaften Gründen",
                    description = "Building and nurturing strong communities based on shared values.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gemeinschaften_gruenden_01",
                            title = "Start a Community",
                            description = "Learn the principles of building and sustaining a community rooted in shared values and mutual respect."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "sprachen_der_liebe_set",
                    title = "Sprachen der Liebe",
                    description = "Understanding the five love languages and improving personal connections.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "sprachen_der_liebe_01",
                            title = "Five Love Languages",
                            description = "Understand and apply the five love languages to strengthen your relationships."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gesund_abgrenzen_set",
                    title = "Gesund Abgrenzen",
                    description = "Setting healthy emotional boundaries to preserve personal well-being.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gesund_abgrenzen_01",
                            title = "Set Boundaries",
                            description = "Learn how to set healthy boundaries to protect your emotional and mental well-being."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gik_set",
                    title = "GIK",
                    description = "A framework for communication and emotional intelligence.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gik_01",
                            title = "GIK Framework",
                            description = "Use the GIK (Gesprächs-Intelligente Kommunikation) framework to enhance communication and emotional intelligence."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "vier_ohren_set",
                    title = "4 Ohren",
                    description = "The 'four ears' model for effective communication.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "vier_ohren_01",
                            title = "Effective Communication",
                            description = "Apply the four ears model to understand communication in different contexts."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "helper_syndrom_set",
                    title = "Helper Syndrome",
                    description = "Recognizing and addressing the tendency to over-help and the impact it has on well-being.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "helper_syndrom_01",
                            title = "Recognize Helper Syndrome",
                            description = "Identify signs of the helper syndrome and learn how to set appropriate limits."
                        )
                    )
                )
            )
        ),
        Subject(
            id_legacy = "ich_und_die_welt_veraendern",
            title = "Ich und die Welt verändern",
            description = "Designed for individuals who wish to make a societal or global impact. Focuses on leadership, social change, and community-building.",
            coachableSets = listOf(
                CoachableSet(
                    author = author,
                    ic_legacy = "kleingruppen_set",
                    title = "Klein-Gruppen",
                    description = "Leveraging small groups for local impact and community development.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "kleingruppen_01",
                            title = "Start Small Groups",
                            description = "Learn how to create and lead small groups that can drive local change."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "juengerschaft_set",
                    title = "Jüngerschaft",
                    description = "Spiritual leadership and discipleship in personal and professional contexts.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "juengerschaft_01",
                            title = "Discipleship",
                            description = "Learn the principles of spiritual leadership and how to mentor others."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "social_triz_set",
                    title = "Social TRIZ",
                    description = "Use TRIZ (Theory of Inventive Problem Solving) for solving complex social challenges.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "social_triz_01",
                            title = "Solve Social Problems",
                            description = "Learn how to apply TRIZ to address social issues and create innovative solutions."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "content_reflektor_set",
                    title = "Content Reflektor",
                    description = "Analyzing content and ideas to drive deeper learning and growth.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "content_reflektor_01",
                            title = "Content Reflection",
                            description = "Use content reflection techniques to deepen learning and uncover new insights."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "coach_werden_set",
                    title = "Coach werden",
                    description = "Developing the skills and mindset to become a professional coach.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "coach_werden_01",
                            title = "Become a Coach",
                            description = "Learn the fundamentals of coaching and start your journey toward becoming a coach."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "gecoacht_werden_set",
                    title = "Gecoacht werden",
                    description = "Learning how to be an effective coachee and benefit from guidance.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "gecoacht_werden_01",
                            title = "Be a Good Coachee",
                            description = "Understand how to receive coaching and maximize your growth through the process."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "coach_sein_set",
                    title = "Coach sein",
                    description = "Becoming a capable and empathetic coach to others.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "coach_sein_01",
                            title = "Be an Effective Coach",
                            description = "Learn the core skills required to become a successful and compassionate coach."
                        )
                    )
                ),
                CoachableSet(
                    author = author,
                    ic_legacy = "peer_coaching_set",
                    title = "Peer Coaching in nach Schritt mit Coach oder ohne",
                    description = "Engaging in peer coaching to support others in their growth.",
                    nuggets = listOf(
                        Nugget(
                            author = author,
                            id_legacy = "peer_coaching_01",
                            title = "Peer Coaching",
                            description = "Practice peer coaching to help others grow while developing your own skills."
                        )
                    )
                )
            )
        )
    )
)
